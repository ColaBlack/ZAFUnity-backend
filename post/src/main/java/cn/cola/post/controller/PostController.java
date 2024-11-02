package cn.cola.post.controller;

import cn.cola.common.common.BaseResponse;
import cn.cola.common.common.ErrorCode;
import cn.cola.common.common.ResultUtils;
import cn.cola.common.exception.ThrowUtils;
import cn.cola.post.model.dto.PublishPostDTO;
import cn.cola.post.service.PostService;
import cn.cola.user.model.vo.UserVO;
import cn.cola.user.utils.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 帖子控制器
 *
 * @author ColaBlack
 */
@RequestMapping("/post")
@RestController
public class PostController {

    @Resource
    private PostService postService;

    @PostMapping("/publish")
    public BaseResponse<Long> publishPost(@RequestBody PublishPostDTO publishPostDTO, @CookieValue(value = "token") String token) {
        UserVO userVO = JwtUtils.verifyAndGetUserVO(token);
        ThrowUtils.throwIf(userVO == null, ErrorCode.NOT_LOGIN_ERROR);
        Long authorId = userVO.getId();
        String title = publishPostDTO.getTitle();
        String content = publishPostDTO.getContent();
        List<String> tags = publishPostDTO.getTags();
        Long ret = postService.publishPost(tags, title, content, authorId);
        return ResultUtils.success(ret);
    }
}