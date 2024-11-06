package cn.cola.service

import cn.cola.model.vo.PostVO
import com.baomidou.mybatisplus.extension.plugins.pagination.Page

interface PostService {
    /**
     * 发布帖子
     */
    fun publishPost(tags: List<String>, title: String, content: String, authorId: Long): Boolean

    /**
     * 搜索帖子
     * @param keywords 关键字
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 帖子列表
     */
    fun searchPost(keywords: String, pageNum: Int, pageSize: Int): Page<PostVO>

    /**
     * 获取帖子详情
     * @param postId 帖子ID
     * @return 帖子详情
     */
    fun getPostDetail(postId: Long): PostVO
}