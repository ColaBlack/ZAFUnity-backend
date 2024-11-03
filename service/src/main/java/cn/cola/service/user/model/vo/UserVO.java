package cn.cola.service.user.model.vo;

import cn.cola.service.user.model.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

/**
 * 用户VO
 *
 * @author ColaBlack
 */
@Data
public class UserVO {
    private User user;

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户账号
     */
    private String userAccount;

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
    private LocalDate userBirthday;

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

    public UserVO(User user) {
        this.user = user;
        BeanUtils.copyProperties(user, this);
    }

    public UserVO() {
    }

}
