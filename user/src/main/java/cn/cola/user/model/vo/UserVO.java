package cn.cola.user.model.vo;

import cn.cola.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

/**
 * 用户VO
 *
 * @author ColaBlack
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserVO {

    private Long id;

    private String userAccount;

    private String email;

    private String userRole;

    private String userAvatar;

    private String userNickname;

    private String userGender;

    private LocalDate userBirthday;

    private String userGrade;

    private String userProfile;

    private String userTags;

    public UserVO(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
