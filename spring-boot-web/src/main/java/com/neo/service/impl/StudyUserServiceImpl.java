package com.neo.service.impl;

import com.neo.Constants;
import com.neo.model.StudyUser;
import com.neo.repository.StudyUserRepository;
import com.neo.service.StudyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author :  yuxuenan 2019年12月24日
 */
@Service(Constants.API_BEAN_NAME_PREFIX + "StudyUserService")
public class StudyUserServiceImpl implements StudyUserService {

    @Autowired
    private StudyUserRepository repository;

    @Override
    public StudyUser findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public void save(String userName, String password) {
        StudyUser user = repository.findByUserName(userName);
        if (user == null) {
            user = new StudyUser();
            user.setUserName(userName);
            user.setPassWord(password);
            repository.save(user);
        }
    }
}
