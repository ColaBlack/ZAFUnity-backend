package cn.cola.user.service.impl

import cn.cola.common.common.BaseResponse
import cn.cola.user.service.UserService
import jakarta.servlet.http.HttpServletRequest

class UserServiceImpl : UserService {
    /**
     * 发送验证码服务
     *
     * @param userAccount 账号
     * @param email       邮箱
     * @param request     servlet请求对象，用于将验证码存入session
     * @return 发送结果
     */
    override fun sendCode(userAccount: String, email: String, request: HttpServletRequest): BaseResponse<String> {
        TODO("Not yet implemented")
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