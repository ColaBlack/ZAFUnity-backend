package cn.cola.post.repo;

import cn.cola.post.model.entity.Post;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 帖子Repo
 *
 * @author ColaBlack
 */
@SoftDelete(columnName = "is_delete")
public interface PostRepo extends JpaRepository<Post, Long>, PagingAndSortingRepository<Post, Long> {


}
