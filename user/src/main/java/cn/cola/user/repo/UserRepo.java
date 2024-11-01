package cn.cola.user.repo;


import cn.cola.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * UserRepo
 *
 * @author ColaBlack
 */
public interface UserRepo extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {


}
