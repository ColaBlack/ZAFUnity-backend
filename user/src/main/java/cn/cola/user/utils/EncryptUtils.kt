package cn.cola.user.utils

import cn.cola.user.constant.UserConstant
import cn.hutool.crypto.SecureUtil

/**
 * 加密工具类
 *
 * @author ColaBlack
 */
class EncryptUtils {

    companion object {
        fun encryptPassword(password: String): String {
            val salt = UserConstant.SALT
            return SecureUtil.sha256(salt + password)
        }
    }
}