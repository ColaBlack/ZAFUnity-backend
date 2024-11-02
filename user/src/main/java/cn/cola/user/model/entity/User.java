package cn.cola.user.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

/**
 * 用户实体类
 *
 * @author ColaBlack
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "user", schema = "zafunity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_account", nullable = false, length = 50)
    private String userAccount;

    @Column(name = "user_password", nullable = false, length = 50)
    private String userPassword;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    @Column(name = "user_role", nullable = false, length = 20)
    private String userRole;

    @Column(name = "user_avatar", length = 200)
    private String userAvatar;

    @Column(name = "user_nickname", length = 50)
    private String userNickname;

    @ColumnDefault("'保密'")
    @Column(name = "user_gender", length = 10)
    private String userGender;

    @Column(name = "user_birthday")
    private LocalDate userBirthday;

    @ColumnDefault("'未知'")
    @Column(name = "user_grade", length = 20)
    private String userGrade;

    @Lob
    @Column(name = "user_profile")
    private String userProfile;

    @Column(name = "user_tags", length = 200)
    private String userTags;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    @ColumnDefault("0")
    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete = false;

}