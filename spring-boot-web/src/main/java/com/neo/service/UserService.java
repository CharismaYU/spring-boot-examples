package com.neo.service;

import com.neo.model.User;

import java.util.List;

/**
 * @author yuxuenan
 */
public interface UserService {

    /**
     * @param userName 用户名
     * @param passWord 密码
     * @return
     */
    User findByNameAndPassWord(String userName, String passWord);

    User findByUserName(String userName);

    List<User> findAll();
}
