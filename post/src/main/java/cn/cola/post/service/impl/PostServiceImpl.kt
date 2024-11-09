package cn.cola.post.service.impl

import cn.cola.common.common.ErrorCode
import cn.cola.common.exception.ThrowUtils
import cn.cola.model.entity.Post
import cn.cola.model.vo.PostVO
import cn.cola.post.constant.PostConst
import cn.cola.post.mapper.PostMapper
import cn.cola.post.service.PostService
import cn.hutool.json.JSONUtil
import com.baomidou.mybatisplus.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import javax.annotation.Resource


@Service
open class PostServiceImpl : PostService, ServiceImpl<PostMapper, Post>() {

    @Resource
    private lateinit var postMapper: PostMapper

    /**
     * 发布帖子
     */
    override fun publishPost(tags: List<String>, title: String, content: String, authorId: Long): Boolean {
        validPost(tags, title, content)
        ThrowUtils.throwIf(authorId <= 0, ErrorCode.PARAMS_ERROR, "作者ID异常")

        val post = Post()
        post.title = title
        post.content = content
        post.postTags = JSONUtil.toJsonStr(tags)
        post.createrId = authorId
        return this.baseMapper.insert(post) == 1
    }

    /**
     * 搜索帖子
     * @param keywords 关键字
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 帖子列表
     */
    override fun searchPost(keywords: String?, pageNum: Int, pageSize: Int): List<PostVO> {
        val posts: List<Post>
        if (keywords.isNullOrBlank()) {
            //分页查询所有帖子
            posts = postMapper.findPostsByPage((pageNum-1)*pageSize, pageSize)
        } else {

            val searchPattern = "%$keywords%"
            posts = postMapper.findPostsByTitleIsLikeOrContentLikeOrPostTagsLike(
                searchPattern,
                searchPattern,
                searchPattern,
                (pageNum-1)*pageSize,
                pageSize
            )
        }
        return posts.map { PostVO(it) }
    }

    /**
     * 获取帖子详情
     * @param postId 帖子ID
     * @return 帖子详情
     */
    override fun getPostDetail(postId: Long): PostVO {
        return PostVO(postMapper.findPostById(postId))
    }


    /**
     * 验证帖子参数
     * @param tags 标签列表
     * @param title 标题
     * @param content 内容
     */
    private fun validPost(tags: List<String>, title: String, content: String) {
        tags.forEach { tag ->
            ThrowUtils.throwIf(tag.isBlank(), ErrorCode.PARAMS_ERROR, "标签不能为空")
            ThrowUtils.throwIf(tag.length > PostConst.POST_TAG_MAX_LENGTH, ErrorCode.PARAMS_ERROR, "标签太长")
        }
        ThrowUtils.throwIf(title.isBlank(), ErrorCode.PARAMS_ERROR, "标题不能为空")
        ThrowUtils.throwIf(title.length > PostConst.POST_TITLE_MAX_LENGTH, ErrorCode.PARAMS_ERROR, "标题太长")
        ThrowUtils.throwIf(content.isBlank(), ErrorCode.PARAMS_ERROR, "内容不能为空")
        ThrowUtils.throwIf(content.length > PostConst.POST_CONTENT_MAX_LENGTH, ErrorCode.PARAMS_ERROR, "内容太长")
    }
}