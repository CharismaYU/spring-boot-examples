package com.neo.service.impl;

import com.neo.Constants;
import com.neo.model.Course;
import com.neo.repository.CourseRepository;
import com.neo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author :  yuxuenan 2019年12月31日
 */
@Service(Constants.API_BEAN_NAME_PREFIX + "CourseService")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository repository;

    @Override
    public Course save(Course course) {
        return repository.save(course);
    }
}
