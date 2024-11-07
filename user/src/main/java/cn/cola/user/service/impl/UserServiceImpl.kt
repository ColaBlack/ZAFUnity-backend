package cn.cola.user.service.impl

import cn.cola.common.common.ErrorCode
import cn.cola.common.constant.UserConstant
import cn.cola.common.exception.ThrowUtils
import cn.cola.common.utils.JwtUtils
import cn.cola.model.entity.User
import cn.cola.model.vo.UserVO
import cn.cola.user.mapper.UserMapper
import cn.cola.user.service.UserService
import cn.cola.user.utils.EncryptUtils
import cn.cola.user.utils.MailUtils
import com.baomidou.mybatisplus.service.impl.ServiceImpl
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit
import javax.annotation.Resource
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Service
open class UserServiceImpl : UserService, ServiceImpl<UserMapper, User>() {

    @Resource
    private lateinit var redissonClient: RedissonClient

    @Resource
    private lateinit var userMapper: UserMapper

    @Resource
    private lateinit var mailUtils: MailUtils

    /**
     * 发送验证码服务
     *
     * @param userAccount 账号
     * @param email       邮箱
     * @return 发送结果
     */
    override fun sendCode(userAccount: String, email: String): String {
        // 从redis缓存中查看是否已经发送了验证码
        val mapCache = redissonClient.getMapCache<String, Int>("code")
        ThrowUtils.throwIf(mapCache.containsKey(email), ErrorCode.FORBIDDEN_ERROR, "验证码已发送，请稍后再试")

        // 验证账号是否合法
        ThrowUtils.throwIf(
            !userAccount.matches(UserConstant.ACCOUNT_REGEX.toRegex()), ErrorCode.PARAMS_ERROR, "账号不合法"
        )
        // 验证邮箱是否合法
        ThrowUtils.throwIf(
            !email.matches(UserConstant.EMAIL_REGEX.toRegex()), ErrorCode.PARAMS_ERROR, "邮箱不合法"
        )

        // 生成验证码并发送至邮箱
        val verificationCode = mailUtils.sendVerificationCode(email)

        // 将验证码存入redis缓存
        mapCache.put(email, verificationCode, 10, TimeUnit.MINUTES)
        return "验证码已发送至邮箱"
    }

    /**
     * 注册服务
     *
     * @param userAccount   账号
     * @param password      密码
     * @param checkPassword 再次输入的确认密码
     * @param email         邮箱
     * @param code          验证码
     * @return 注册结果
     */
    override fun register(
        userAccount: String,
        password: String,
        checkPassword: String,
        email: String,
        code: String,
    ): String {
        // 验证邮箱是否合法
        ThrowUtils.throwIf(
            !email.matches(UserConstant.EMAIL_REGEX.toRegex()), ErrorCode.PARAMS_ERROR, "邮箱不合法"
        )

        // 从redis缓存中查看验证码是否正确
        val mapCache = redissonClient.getMapCache<String, Int>("code")
        ThrowUtils.throwIf(
            !mapCache.containsKey(email), ErrorCode.FORBIDDEN_ERROR, "验证码不存在或已过期"
        )
        ThrowUtils.throwIf(
            mapCache[email] != code.toInt(), ErrorCode.FORBIDDEN_ERROR, "验证码错误"
        )

        // 验证账号是否合法
        ThrowUtils.throwIf(
            !userAccount.matches(UserConstant.ACCOUNT_REGEX.toRegex()), ErrorCode.PARAMS_ERROR, "账号不合法"
        )
        // 验证密码是否合法
        ThrowUtils.throwIf(
            !password.matches(UserConstant.PASSWORD_REGEX.toRegex()), ErrorCode.PARAMS_ERROR, "密码不合法"
        )
        // 验证确认密码是否正确
        ThrowUtils.throwIf(
            password != checkPassword, ErrorCode.PARAMS_ERROR, "两次输入的密码不一致"
        )

        val encryptedPassword = EncryptUtils.encryptPassword(password)
        val user = User()
        user.userAccount = userAccount
        user.userPassword = encryptedPassword
        user.email = email

        // 加锁，防止并发注册
        val lock = redissonClient.getLock("registerLock${userAccount}")
        try {
            if (lock.tryLock(10, 30, TimeUnit.SECONDS)) {
                // 验证账号是否已存在
                ThrowUtils.throwIf(
                    userMapper.existsByUserAccount(userAccount), ErrorCode.PARAMS_ERROR, "账号已存在"
                )

                this.baseMapper.insert(user)
            } else {
                // 获取锁失败，可以抛出异常或返回错误
                throw Exception("获取锁失败，请稍后重试")
            }
        } finally {
            if (lock.isHeldByCurrentThread) {
                lock.unlock()
            }
        }

        // 注册成功，清除验证码缓存
        mapCache.remove(email)

        return "注册成功"
    }

    /**
     * 登录服务
     *
     * @param userAccount 账号
     * @param password    密码
     * @param request     servlet请求对象，用于从cookie中清除旧的jwt
     * @param response     servlet响应对象，用于将jwt存入cookie
     * @return 登录结果
     */
    override fun login(
        userAccount: String, password: String, request: HttpServletRequest, response: HttpServletResponse
    ): UserVO {
        validLoginParam(userAccount, password)

        val encryptedPassword = EncryptUtils.encryptPassword(password)

        val user = userMapper.findByUserAccountAndUserPassword(userAccount, encryptedPassword)

        if (user == null) {
            ThrowUtils.throwIf(true, ErrorCode.FORBIDDEN_ERROR, "账号或密码错误")
        }

        val userVO = UserVO(user)
        // 生成jwt
        val jwt = JwtUtils.generateToken(userVO)

        // 清除旧的jwt
        val oldToken = request.cookies?.firstOrNull { it.name == UserConstant.USER_LOGIN_STATE }
        if (oldToken != null) {
            oldToken.value = ""
            oldToken.maxAge = 0
            response.addCookie(oldToken)
        }

        // 将jwt存入redis
        redissonClient.getMapCache<String, String>(UserConstant.USER_LOGIN_STATE)
            .put(user.userId.toString(), jwt, UserConstant.JWT_EXPIRE, TimeUnit.SECONDS)

        // 将jwt存入cookie
        response.addCookie(Cookie(UserConstant.USER_LOGIN_STATE, jwt))

        return userVO
    }

    /**
     * 注销服务
     *
     * @param request servlet请求对象，用于获取旧cookie
     * @param response servlet响应对象，用于清除cookie
     * @return 注销结果
     */
    override fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        val oldToken = request.cookies?.firstOrNull { it.name == UserConstant.USER_LOGIN_STATE }
        // 清除redis缓存
        val tokenMap = redissonClient.getMapCache<String, String>(UserConstant.USER_LOGIN_STATE)
        val userId = JwtUtils.verifyAndGetUserVO(oldToken?.value)
        tokenMap.remove(userId.toString())
        // 清除cookie
        if (oldToken != null) {
            oldToken.value = ""
            oldToken.maxAge = 0
            response.addCookie(oldToken)
        }
        return "注销成功"
    }

    /**
     * 修改密码服务
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param request     servlet请求对象，用于从cookie中获取jwt
     * @return 修改结果
     */
    override fun modifyPassword(
        oldPassword: String, newPassword: String, request: HttpServletRequest
    ): String {
        TODO("Not yet implemented")
    }

    /**
     * 忘记密码服务
     *
     * @param userAccount 账号
     * @param email       邮箱
     * @param code        验证码
     * @param password    新密码
     * @param request     servlet请求对象，用于从session中获取正确的验证码
     * @return 忘记密码结果
     */
    override fun forgetPassword(
        userAccount: String, email: String, code: String, password: String, request: HttpServletRequest
    ): String {
        TODO("Not yet implemented")
    }

    /**
     * 验证登录参数
     * @param userAccount 账号
     * @param password 密码
     */
    private fun validLoginParam(userAccount: String, password: String) {
        // 验证账号是否合法
        ThrowUtils.throwIf(
            !userAccount.matches(UserConstant.ACCOUNT_REGEX.toRegex()), ErrorCode.PARAMS_ERROR, "账号不合法"
        )
        // 验证密码是否合法
        ThrowUtils.throwIf(
            !password.matches(UserConstant.PASSWORD_REGEX.toRegex()), ErrorCode.PARAMS_ERROR, "密码不合法"
        )
    }
}