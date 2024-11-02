package cn.cola.post.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

/**
 * 帖子实体类
 *
 * @author ColaBlack
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post", schema = "zafunity")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "creater_id", nullable = false)
    private Long createrId;

    @ColumnDefault("0")
    @Column(name = "starts", nullable = false)
    private Integer starts;

    @Column(name = "post_tags", length = 200)
    private String postTags;

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