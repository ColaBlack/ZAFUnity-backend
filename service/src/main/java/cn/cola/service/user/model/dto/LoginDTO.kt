package cn.cola.service.user.model.dto

/**
 * 登录数据传输对象
 *
 * @author ColaBlack
 */
data class LoginDTO(
    /** 用户账号 */
    val userAccount: String,
    /** 用户密码 */
    val password: String
)
