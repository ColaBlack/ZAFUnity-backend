package cn.cola.common.utils

import jakarta.annotation.Resource
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import kotlin.random.Random

/**
 * 邮箱工具类
 *
 * @author ColaBlack
 */
object MailUtils {

    @Resource
    private lateinit var sender: JavaMailSender

    @Value("\${spring.mail.username}")
    private lateinit var from: String

    /**
     * 通用发送邮件
     *
     * @param title 邮件标题
     * @param content 邮件内容
     * @param receiver 收件人邮箱地址
     * @return 是否发送成功
     */
    fun sendMail(title: String, content: String, receiver: String): Boolean {
        val message = SimpleMailMessage()
        message.subject = title
        message.text = content
        message.from = from
        message to receiver
        sender.send(message)
        return true
    }

    /**
     * 发送验证码
     *
     * @param receiver 收件人邮箱地址
     * @return 验证码
     */
    fun sendVerificationCode(receiver: String): Int {
        val code = Random(114514).nextInt(100000, 999999)
        val title = "【林友汇】你的验证码"
        val text = """
            欢迎注册林友汇，您的验证码为：${code}。请在10分钟内输入验证码完成注册。
        """.trimIndent()
        sendMail(title, text, receiver)
        return code
    }
}