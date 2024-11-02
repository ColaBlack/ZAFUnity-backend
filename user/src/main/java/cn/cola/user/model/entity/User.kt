package cn.cola.user.model.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.time.Instant
import java.time.LocalDate

/**
 * 用户PO
 *
 * @author ColaBlack
 */
@Entity
@Table(name = "user", schema = "zafunity")
open class User {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    open var id: Long? = null

    /**
     * 用户账号
     */
    @Column(name = "user_account", nullable = false, length = 50)
    open var userAccount: String? = null

    /**
     * 用户密码
     */
    @Column(name = "user_password", nullable = false, length = 50)
    open var userPassword: String? = null

    /**
     * 用户邮箱
     */
    @Column(name = "email", nullable = false, length = 50)
    open var email: String? = null

    /**
     * 用户角色 user/admin/ban
     */
    @ColumnDefault("'user'")
    @Column(name = "user_role", nullable = false, length = 20)
    open var userRole: String? = null

    /**
     * 用户头像
     */
    @Column(name = "user_avatar", length = 200)
    open var userAvatar: String? = null

    /**
     * 用户昵称
     */
    @Column(name = "user_nickname", length = 50)
    open var userNickname: String? = null

    /**
     * 用户性别
     */
    @ColumnDefault("'保密'")
    @Column(name = "user_gender", length = 10)
    open var userGender: String? = null

    /**
     * 用户生日
     */
    @Column(name = "user_birthday")
    open var userBirthday: LocalDate? = null

    /**
     * 用户年级
     */
    @ColumnDefault("'未知'")
    @Column(name = "user_grade", length = 20)
    open var userGrade: String? = null

    /**
     * 用户简介
     */
    @Lob
    @Column(name = "user_profile")
    open var userProfile: String? = null

    /**
     * 用户标签(JSON)
     */
    @Column(name = "user_tags", length = 200)
    open var userTags: String? = null

    /**
     * 创建时间
     */
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_time", nullable = false)
    open var createTime: Instant? = null

    /**
     * 更新时间
     */
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "update_time", nullable = false)
    open var updateTime: Instant? = null

    /**
     * 是否删除 0未删除 1已删除
     */
    @ColumnDefault("0")
    @Column(name = "is_delete", nullable = false)
    open var isDelete: Boolean? = false
}