package cn.cola.model.dto.post

/**
 * 发布帖子DTO
 *
 * @author ColaBlack
 */
data class PublishPostDTO(
    /** 标题 */
    var title: String,
    /** 内容 */
    var content: String,
    /** 标签 */
    var tags: List<String>,
)