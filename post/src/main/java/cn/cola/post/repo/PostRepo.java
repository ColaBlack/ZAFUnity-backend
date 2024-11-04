package cn.cola.post.repo;

import cn.cola.model.entity.Post;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 帖子Repo
 *
 * @author ColaBlack
 */
@SoftDelete(columnName = "is_delete")
public interface PostRepo extends JpaRepository<Post, Long>, PagingAndSortingRepository<Post, Long> {

    /**
     * 根据标题、内容、标签模糊查询帖子
     *
     * @param title    标题
     * @param content  内容
     * @param postTags 标签
     * @param pageable 分页
     * @return 分页结果
     */
    Page<Post> findPostsByTitleIsLikeOrContentLikeOrPostTagsLike(String title, String content, String postTags, Pageable pageable);
}
