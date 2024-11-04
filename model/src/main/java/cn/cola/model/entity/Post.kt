package cn.cola.model.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

/**
 * 帖子表
 */
@Entity
@Table(name = "post", schema = "zafunity")
open class Post {
    /**
     * 帖子ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    open var id: Long? = null

    /**
     * 帖子标题
     */
    @Column(name = "title", nullable = false, length = 50)
    open var title: String? = null

    /**
     * 帖子内容
     */
    @Lob
    @Column(name = "content", nullable = false)
    open var content: String? = null

    /**
     * 发帖用户ID
     */
    @Column(name = "creater_id", nullable = false)
    open var createrId: Long? = null

    /**
     * 帖子标签(JSON)
     */
    @Column(name = "post_tags", length = 200)
    open var postTags: String? = null

    /**
     * 帖子点赞数
     */
    @ColumnDefault("0")
    @Column(name = "starts", nullable = false)
    open var starts: Int? = null

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