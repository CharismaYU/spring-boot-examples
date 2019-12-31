package com.neo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 课程
 *
 * @author :  yuxuenan 2019年12月31日
 */
@Data
@Entity
public class Course implements Serializable {

    private static final long serialVersionUID = -7865296568965759038L;

    @Id
    @GeneratedValue
    private Long id;

    private String courseTitle;

    private String courseCode;

    private String courseId;

    private boolean userFinish;
}
