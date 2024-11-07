package cn.cola.model.vo;


import cn.cola.model.entity.Post;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * 帖子VO
 *
 * @author ColaBlack
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostVO {

    /**
     * 帖子ID
     */
    private Long id;

    /**
     * 帖子标题
     */
    private String title;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 发帖用户ID
     */
    private Long createrId;

    /**
     * 帖子点赞数
     */
    private Integer starts;

    /**
     * 帖子标签(JSON)
     */
    private List<String> postTags;

    /**
     * 创建时间
     */
    private Instant createTime;

    /**
     * 更新时间
     */
    private Instant updateTime;

    public PostVO(Post post) {
        Long postId = post.getPostId();
        String title = post.getTitle();
        String content = post.getContent();
        Long createrId = post.getCreaterId();
        Integer starts = post.getStarts();
        String postTags = post.getPostTags();
        Date createTime = post.getCreateTime();
        Date updateTime = post.getUpdateTime();
        this.setId(postId);
        this.setTitle(title);
        this.setContent(content);
        this.setCreaterId(createrId);
        this.setStarts(starts);
        this.setPostTags(JSONUtil.toList(postTags, String.class));
        this.setCreateTime(createTime.toInstant());
        this.setUpdateTime(updateTime.toInstant());
    }


}
