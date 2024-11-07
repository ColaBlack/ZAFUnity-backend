package cn.cola.post.mapper;

import cn.cola.model.entity.Post;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

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
     * @param page     页码
     * @param size     页大小
     * @return 分页结果
     */
    @Select("SELECT * FROM post WHERE title LIKE CONCAT('%', #{title}, '%') OR content LIKE CONCAT('%', #{content}, '%') OR post_tags LIKE CONCAT('%', #{postTags}, '%') LIMIT #{page}, #{size}")
    Page<Post> findPostsByTitleIsLikeOrContentLikeOrPostTagsLike(String title, String content, String postTags, int page, int size);

    /**
     * 分页查询所有帖子
     *
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Select("SELECT * FROM post LIMIT #{page}, #{size}")
    Page<Post> findPostsByPage(int page, int size);

    /**
     * 按帖子ID查询帖子
     *
     * @param id 帖子id
     * @return 帖子
     */
    @Select("SELECT * FROM post WHERE post_id = #{id}")
    Optional<Post> findPostById(Long id);
}




