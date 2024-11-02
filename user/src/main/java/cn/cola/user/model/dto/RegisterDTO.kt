package cn.cola.user.model.dto

/**
 * 注册数据传输对象
 *
 * @author ColaBlack
 */
data class RegisterDTO(
    /** 用户账号 */
    val userAccount: String,
    /** 用户密码 */
    val password: String,
    /** 确认密码 */
    val checkPassword: String,
    /** 用户邮箱 */
    val email: String,
    /** 验证码 */
    val code: String
)
