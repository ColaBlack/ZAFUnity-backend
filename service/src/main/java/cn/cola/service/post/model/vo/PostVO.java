package cn.cola.service.post.model.vo;

import cn.cola.service.post.model.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.Instant;
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
        BeanUtils.copyProperties(post, this);
    }


}
