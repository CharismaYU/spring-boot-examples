package com.neo.repository;

import com.neo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuxuenan
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 通过用户名查找用户
     *
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 通过用户名和密码查找用户
     *
     * @param userName
     * @param password
     * @return
     */
    User findByUserNameAndPassWord(String userName, String password);

    /**
     * 通过用户名或邮箱查找用户
     *
     * @param username
     * @param email
     * @return
     */
    User findByUserNameOrEmail(String username, String email);

}