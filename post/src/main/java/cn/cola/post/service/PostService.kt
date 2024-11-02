package cn.cola.post.service

interface PostService {
    /**
     * 发布帖子
     */
    fun publishPost(tags: List<String>, title: String, content: String, authorId: Long): Long
}