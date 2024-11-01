package cn.cola.user.service

import cn.cola.common.common.BaseResponse
import jakarta.servlet.http.HttpServletRequest

/**
 * 用户服务接口
 *
 * @author ColaBlack
 */
interface UserService {

    /**
     * 发送验证码服务
     *
     * @param userAccount 账号
     * @param email       邮箱
     * @param request     servlet请求对象，用于将验证码存入session
     * @return 发送结果
     */
    fun sendCode(
        userAccount: String,
        email: String,
        request: HttpServletRequest
    ): BaseResponse<String>

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
    fun register(
        userAccount: String,
        password: String,
        checkPassword: String,
        email: String,
        code: String,
        request: HttpServletRequest
    ): BaseResponse<String>

    /**
     * 登录服务
     *
     * @param userAccount 账号
     * @param password    密码
     * @param request     servlet请求对象，用于将jwt存入cookie
     * @return 登录结果
     */
    fun login(
        userAccount: String,
        password: String,
        request: HttpServletRequest
    ): BaseResponse<String>

    /**
     * 注销服务
     *
     * @param request servlet请求对象，用于清除cookie
     * @return 注销结果
     */
    fun logout(
        request: HttpServletRequest
    ): BaseResponse<String>

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
    ): BaseResponse<String>

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
    ): BaseResponse<String>
}