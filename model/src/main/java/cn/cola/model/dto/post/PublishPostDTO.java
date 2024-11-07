package cn.cola.model.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 发布帖子DTO
 *
 * @author ColaBlack
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishPostDTO {
    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签
     */
    private List<String> tags;

}
