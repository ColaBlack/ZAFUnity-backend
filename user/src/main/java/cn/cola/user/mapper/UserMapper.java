package cn.cola.user.mapper;

import cn.cola.model.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Administrator
 * @description 针对表【user(用户表)】的数据库操作Mapper
 * @createDate 2024-11-06 18:35:56
 * @Entity cn.cola.model.entity.User
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户账号查询用户是否存在
     *
     * @param userAccount 用户账号
     * @return true：存在，false：不存在
     */
    @Select("SELECT COUNT(*) FROM user WHERE user_account = #{userAccount}")
    boolean existsByUserAccount(String userAccount);

    /**
     * 根据用户账号和密码查询用户
     *
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @return 用户实体，为空表示用户不存在或密码错误
     */
    @Select("SELECT * FROM user WHERE user_account = #{userAccount} AND user_password = #{userPassword}")
    User findByUserAccountAndUserPassword(String userAccount, String userPassword);

}




