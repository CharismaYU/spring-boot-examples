package com.neo.service.impl;

import com.neo.Constants;
import com.neo.model.User;
import com.neo.repository.UserRepository;
import com.neo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :  yuxuenan 2019年12月24日
 */
@Service(Constants.API_BEAN_NAME_PREFIX + "UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getOne(Long userId) {
        return userRepository.getOne(userId);
    }

    @Override
    public User findByNameAndPassWord(String userName, String passWord) {
        return userRepository.findByUserNameAndPassWord(userName, passWord);
    }

    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean updatePassword(Long userId, String originalPassword, String newPassword) {
        User user = userRepository.getOne(userId);
        if (user == null) {
            return false;
        }
        //比较原密码是否正确
        if (originalPassword.equals(user.getPassWord())) {
            user.setPassWord(newPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateName(Long userId, String userName, String nickName) {
        User user = userRepository.getOne(userId);
        if (user == null) {
            return false;
        }
        //比较原密码是否正确
        user.setUserName(userName);
        user.setNickName(nickName);
        userRepository.save(user);
        return true;
    }
}