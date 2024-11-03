package cn.cola.post.service.impl

import cn.cola.common.common.ErrorCode
import cn.cola.common.exception.BusinessException
import cn.cola.common.exception.ThrowUtils
import cn.cola.post.constant.PostConst
import cn.cola.post.repo.PostRepo
import cn.cola.service.post.model.entity.Post
import cn.cola.service.post.model.vo.PostVO
import cn.cola.service.post.service.PostService
import cn.cola.service.user.service.UserService
import cn.hutool.json.JSONUtil
import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PostServiceImpl : PostService {


    private lateinit var usersService: UserService

    @Resource
    private lateinit var postRepo: PostRepo

    /**
     * 发布帖子
     */
    override fun publishPost(tags: List<String>, title: String, content: String, authorId: Long): Long {
        validPost(tags, title, content)
        ThrowUtils.throwIf(!usersService.existsById(authorId), ErrorCode.PARAMS_ERROR, "作者ID异常")

        val post = Post()
        post.title = title
        post.content = content
        post.postTags = JSONUtil.toJsonStr(tags)
        post.createrId = authorId
        val ret = postRepo.save(post).id
        ThrowUtils.throwIf(ret == null, ErrorCode.SYSTEM_ERROR, "发布帖子失败")
        return ret!!
    }

    /**
     * 搜索帖子
     * @param keywords 关键字
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 帖子列表
     */
    override fun searchPost(keywords: String, pageNum: Int, pageSize: Int): Page<PostVO> {
        ThrowUtils.throwIf(keywords.isBlank(), ErrorCode.PARAMS_ERROR, "关键词不能为空")

        val searchPattern = "%$keywords%"
        val posts = postRepo.findPostsByTitleIsLikeOrContentLikeOrPostTagsLike(
            searchPattern,
            searchPattern,
            searchPattern,
            PageRequest.of(pageNum, pageSize)
        )

        // 将帖子转化为PostVO的列表
        posts.map { PostVO(it) }

        // 返回Page对象时，保持与原posts的分页信息
        return posts.map { PostVO(it) }
    }

    /**
     * 获取帖子详情
     * @param postId 帖子ID
     * @return 帖子详情
     */
    override fun getPostDetail(postId: Long): PostVO {
        postRepo.findById(postId).orElseThrow {
            BusinessException(ErrorCode.NOT_FOUND_ERROR, "帖子不存在")
        }
        return PostVO(postRepo.findById(postId).get())
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