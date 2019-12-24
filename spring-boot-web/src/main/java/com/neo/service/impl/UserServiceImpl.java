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
}
