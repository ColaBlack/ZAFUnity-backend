package cn.cola.post.service

import cn.cola.post.model.vo.PostVO
import org.springframework.data.domain.Page

interface PostService {
    /**
     * 发布帖子
     */
    fun publishPost(tags: List<String>, title: String, content: String, authorId: Long): Long

    /**
     * 搜索帖子
     * @param keywords 关键字
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 帖子列表
     */
    fun searchPost(keywords: String, pageNum: Int, pageSize: Int): Page<PostVO>
}