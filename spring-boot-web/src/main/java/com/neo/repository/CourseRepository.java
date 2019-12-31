package com.neo.repository;

import com.neo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author :  yuxuenan 2019年12月31日
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

}
