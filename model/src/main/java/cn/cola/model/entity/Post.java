package cn.cola.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子表
 *
 * @author ColaBlack
 */
@TableName(value = "post")
@Data
public class Post implements Serializable {
    /**
     * 帖子ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private Long postId;

    /**
     * 帖子标题
     */
    private String title;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 用户ID
     */
    private Long createrId;

    /**
     * 帖子点赞数
     */
    private Integer starts;

    /**
     * 帖子标签(JSON)
     */
    private String postTags;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 0未删除 1已删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}