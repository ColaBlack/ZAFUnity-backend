package cn.cola.post.controller;

import cn.cola.common.common.BaseResponse;
import cn.cola.common.common.ErrorCode;
import cn.cola.common.common.ResultUtils;
import cn.cola.common.constant.UserConstant;
import cn.cola.common.exception.ThrowUtils;
import cn.cola.common.utils.JwtUtils;
import cn.cola.model.dto.post.PublishPostDTO;
import cn.cola.model.vo.PostVO;
import cn.cola.model.vo.UserVO;
import cn.cola.service.PostService;
import cn.cola.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 帖子控制器
 *
 * @author ColaBlack
 */
@RestController
public class PostController {

    @Resource
    private PostService postService;

    @Resource
    private UserService userService;

    /**
     * 发布帖子
     */
    @PostMapping("/publish")
    public BaseResponse<Long> publishPost(@RequestBody PublishPostDTO publishPostDTO,
                                          @CookieValue(value = UserConstant.USER_LOGIN_STATE) String token) {
        UserVO userVO = JwtUtils.verifyAndGetUserVO(token);
        ThrowUtils.throwIf(userService.validLoginStatus(token), ErrorCode.NOT_LOGIN_ERROR);
        ThrowUtils.throwIf(userVO == null, ErrorCode.NOT_LOGIN_ERROR);
        Long authorId = userVO.getId();
        String title = publishPostDTO.getTitle();
        String content = publishPostDTO.getContent();
        List<String> tags = publishPostDTO.getTags();
        Long ret = postService.publishPost(tags, title, content, authorId);
        return ResultUtils.success(ret);
    }

    /**
     * 搜索帖子
     */
    @GetMapping("/search")
    public BaseResponse<Page<PostVO>> searchPost(@RequestParam(value = "keyword") String keyword,
                                                 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                 @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        ThrowUtils.throwIf(page < 1, ErrorCode.PARAMS_ERROR, "页码不能小于1");
        ThrowUtils.throwIf(size < 1, ErrorCode.PARAMS_ERROR, "每页条数不能小于1");
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR, "每页条数不能大于20");
        Page<PostVO> ret = postService.searchPost(keyword, page, size);
        return ResultUtils.success(ret);
    }

    /**
     * 查看帖子详情
     */
    @GetMapping("/get/vo/{postId}")
    public BaseResponse<PostVO> getPostVO(@PathVariable("postId") Long postId) {
        PostVO ret = postService.getPostDetail(postId);
        return ResultUtils.success(ret);
    }
}