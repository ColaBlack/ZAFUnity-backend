package cn.cola.post.mapper;

import cn.cola.model.entity.Post;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【post(帖子表)】的数据库操作Mapper
 * @createDate 2024-11-06 18:48:25
 * @Entity cn.cola.model.entity.Post
 */
public interface PostMapper extends BaseMapper<Post> {
    /**
     * 根据标题、内容、标签模糊查询帖子
     *
     * @param title    标题
     * @param content  内容
     * @param postTags 标签
     * @param offset   偏移量
     * @param size     页大小
     * @return 分页结果
     */
    @Select("SELECT * FROM post WHERE title LIKE CONCAT('%', #{title}, '%') OR content LIKE CONCAT('%', #{content}, '%') OR post_tags LIKE CONCAT('%', #{postTags}, '%') LIMIT #{offset}, #{size}")
    List<Post> findPostsByTitleIsLikeOrContentLikeOrPostTagsLike(@Param("title") String title,
                                                                 @Param("content") String content,
                                                                 @Param("postTags") String postTags,
                                                                 @Param("offset") int offset,
                                                                 @Param("size") int size);

    /**
     * 分页查询所有帖子
     *
     * @param offset   偏移量
     * @param pageSize 页大小
     * @return 分页结果
     */
    @Select("SELECT * FROM post LIMIT #{offset}, #{pageSize}")
    List<Post> findPostsByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * 按帖子ID查询帖子
     *
     * @param id 帖子id
     * @return 帖子
     */
    @Select("SELECT * FROM post WHERE post_id = #{id}")
    Post findPostById(@Param("id") Long id);
}




