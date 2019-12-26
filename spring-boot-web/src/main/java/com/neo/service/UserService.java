package com.neo.service;

import com.neo.model.User;

import java.util.List;

/**
 * @author yuxuenan
 */
public interface UserService {

    User getOne(Long userId);

    /**
     * 通过用户名和密码查找用户
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return
     */
    User findByNameAndPassWord(String userName, String passWord);

    /**
     * 通过用户名查找用户
     *
     * @param userName 用户名
     * @return
     */
    User findByUserName(String userName);

    /**
     * 查找所有用户
     *
     * @return
     */
    List<User> findAll();

    /**
     * 修改用户密码
     *
     * @param loginUserId      用户ID
     * @param originalPassword 旧密码
     * @param newPassword      新密码
     * @return
     */
    boolean updatePassword(Long loginUserId, String originalPassword, String newPassword);

    /**
     * 修改用户信息
     *
     * @param userId   用户ID
     * @param userName 用户名
     * @param nickName 昵称
     * @return
     */
    boolean updateName(Long userId, String userName, String nickName);
}
