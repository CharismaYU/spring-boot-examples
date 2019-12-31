package com.neo.service;

import com.neo.model.StudyUser;

/**
 * @author :  yuxuenan 2019年12月24日
 */
public interface StudyUserService {

    /**
     * @param userName
     * @return
     */
    StudyUser findByUserName(String userName);

    /**
     * @param username
     * @param password
     */
    void save(String username, String password);
}
