package com.neo.service.impl;

import com.neo.model.Course;
import com.neo.repository.CourseRepository;
import com.neo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author :  yuxuenan 2019年12月31日
 */
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository repository;

    @Override
    public Course save(Course course) {
        return repository.save(course);
    }
}
