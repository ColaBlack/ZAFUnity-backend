package cn.cola.service

import cn.cola.model.vo.UserVO
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse


/**
 * 用户服务接口
 *
 * @author ColaBlack
 */
//@FeignClient(name = "user-service", path = "/api/user/inner")
interface UserService {

    /**
     * 发送验证码服务
     *
     * @param userAccount 账号
     * @param email       邮箱
     * @return 发送结果
     */
    fun sendCode(
        userAccount: String,
        email: String,
    ): String

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
    fun register(
        userAccount: String,
        password: String,
        checkPassword: String,
        email: String,
        code: String,
    ): String

    /**
     * 登录服务
     *
     * @param userAccount 账号
     * @param password    密码
     * @param request     servlet请求对象，用于从cookie中清除旧的jwt
     * @param response     servlet响应对象，用于将jwt存入cookie
     * @return 登录结果
     */
    fun login(
        userAccount: String,
        password: String,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): cn.cola.model.vo.UserVO

    /**
     * 注销服务
     *
     * @param request servlet请求对象，用于获取旧cookie
     * @param response servlet响应对象，用于清除cookie
     * @return 注销结果
     */
    fun logout(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): String

    /**
     * 修改密码服务
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param request     servlet请求对象，用于从cookie中获取jwt
     * @return 修改结果
     */
    fun modifyPassword(
        oldPassword: String,
        newPassword: String,
        request: HttpServletRequest
    ): String

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
    fun forgetPassword(
        userAccount: String,
        email: String,
        code: String,
        password: String,
        request: HttpServletRequest
    ): String

    /**
     * 验证登录状态
     * @param token 登录token
     * @return 验证结果
     */
    fun validLoginStatus(token: String): Boolean
}