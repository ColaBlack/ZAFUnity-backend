package cn.cola.model.vo;


import cn.cola.model.entity.User;
import lombok.Data;

import java.util.Date;

/**
 * 用户VO
 *
 * @author ColaBlack
 */
@Data
public class UserVO {
    /**
     * 用户ID
     */
    private Long userId;

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

    public UserVO(User user) {
        Long userId = user.getUserId();
        String userAccount = user.getUserAccount();
        String email = user.getEmail();
        String userRole = user.getUserRole();
        String userAvatar = user.getUserAvatar();
        String userNickname = user.getUserNickname();
        String userGender = user.getUserGender();
        Date userBirthday = user.getUserBirthday();
        String userGrade = user.getUserGrade();
        String userProfile = user.getUserProfile();
        String userTags = user.getUserTags();
        this.setUserId(userId);
        this.setUserAccount(userAccount);
        this.setEmail(email);
        this.setUserRole(userRole);
        this.setUserAvatar(userAvatar);
        this.setUserNickname(userNickname);
        this.setUserGender(userGender);
        this.setUserBirthday(userBirthday);
        this.setUserGrade(userGrade);
        this.setUserProfile(userProfile);
        this.setUserTags(userTags);

    }

    public UserVO() {
    }

}
