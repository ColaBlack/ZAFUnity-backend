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

    /**
     * 根据用户账号查询用户是否存在
     *
     * @param userAccount 用户账号
     * @return true：存在，false：不存在
     */
    boolean existsByUserAccount(String userAccount);

    /**
     * 根据用户账号和密码查询用户
     *
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @return 用户实体，为空表示用户不存在或密码错误
     */
    User findByUserAccountAndUserPassword(String userAccount, String userPassword);
}
