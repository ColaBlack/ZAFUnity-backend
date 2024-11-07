package cn.cola.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @author ColaBlack
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private Long userId;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户角色 user/admin/ban
     */
    private String userRole;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户昵称
     */
    private String userNickname;

    /**
     * 用户性别
     */
    private String userGender;

    /**
     * 用户生日
     */
    private Date userBirthday;

    /**
     * 用户年级
     */
    private String userGrade;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户标签(JSON)
     */
    private String userTags;

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