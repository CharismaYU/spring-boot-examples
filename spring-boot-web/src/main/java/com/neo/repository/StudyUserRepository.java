package com.neo.repository;

import com.neo.model.StudyUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author :  yuxuenan 2019年12月24日
 */
public interface StudyUserRepository extends JpaRepository<StudyUser, Long> {

    /**
     * 通过用户名查找用户
     *
     * @param userName
     * @return
     */
    StudyUser findByUserName(String userName);

}
