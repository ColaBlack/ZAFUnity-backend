package cn.cola.user.service.impl

import cn.cola.common.common.BaseResponse
import cn.cola.common.common.ErrorCode
import cn.cola.common.common.ResultUtils
import cn.cola.common.common.exception.ThrowUtils
import cn.cola.user.constant.UserConstant
import cn.cola.user.service.UserService
import cn.cola.user.utils.MailUtils
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import org.redisson.api.RedissonClient
import java.util.concurrent.TimeUnit

class UserServiceImpl : UserService {

    @Resource
    private lateinit var redissonClient: RedissonClient

    /**
     * 发送验证码服务
     *
     * @param userAccount 账号
     * @param email       邮箱
     * @return 发送结果
     */
    override fun sendCode(userAccount: String, email: String): BaseResponse<String> {
        // 从redis缓存中查看是否已经发送了验证码
        val mapCache = redissonClient.getMapCache<String, Int>("code")
        ThrowUtils.throwIf(mapCache.containsKey(email), ErrorCode.FORBIDDEN_ERROR, "验证码已发送，请稍后再试")

        // 验证账号是否合法
        ThrowUtils.throwIf(
            userAccount.matches(UserConstant.ACCOUNT_REGEX.toRegex()),
            ErrorCode.PARAMS_ERROR,
            "账号不合法"
        )
        // 验证邮箱是否合法
        ThrowUtils.throwIf(
            email.matches(UserConstant.EMAIL_REGEX.toRegex()),
            ErrorCode.PARAMS_ERROR,
            "邮箱不合法"
        )

        // 生成验证码并发送至邮箱
        val verificationCode = MailUtils.sendVerificationCode(email)

        // 将验证码存入redis缓存
        mapCache.put(email, verificationCode, 10, TimeUnit.MINUTES)
        return ResultUtils.success("验证码已发送至邮箱")
    }

    /**
     * 注册服务
     *
     * @param userAccount   账号
     * @param password      密码
     * @param checkPassword 再次输入的确认密码
     * @param email         邮箱
     * @param code          验证码
     * @param request       servlet请求对象，用于从session中获取正确的验证码
     * @return 注册结果
     */
    override fun register(
        userAccount: String,
        password: String,
        checkPassword: String,
        email: String,
        code: String,
        request: HttpServletRequest
    ): BaseResponse<String> {
        TODO("Not yet implemented")
    }

    /**
     * 登录服务
     *
     * @param userAccount 账号
     * @param password    密码
     * @param request     servlet请求对象，用于将jwt存入cookie
     * @return 登录结果
     */
    override fun login(userAccount: String, password: String, request: HttpServletRequest): BaseResponse<String> {
        TODO("Not yet implemented")
    }

    /**
     * 注销服务
     *
     * @param request servlet请求对象，用于清除cookie
     * @return 注销结果
     */
    override fun logout(request: HttpServletRequest): BaseResponse<String> {
        TODO("Not yet implemented")
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
        oldPassword: String,
        newPassword: String,
        request: HttpServletRequest
    ): BaseResponse<String> {
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
        userAccount: String,
        email: String,
        code: String,
        password: String,
        request: HttpServletRequest
    ): BaseResponse<String> {
        TODO("Not yet implemented")
    }
}