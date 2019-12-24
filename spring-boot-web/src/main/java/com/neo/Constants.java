package com.neo;

/**
 * @author :  yuxuenan 2019年12月24日
 */
public interface Constants {

    /**
     * Spring Bean前缀
     */
    String API_BEAN_NAME_PREFIX = "api";

    /**
     * 登录
     */
    String URL_LOGIN = "http://study.foton.com.cn/login/login.ajaxLogin.do";

    /**
     * 查询课程列表
     */
    String URL_NEW_COURSELIST = "http://study.foton.com.cn/els/html/courseCenter/courseCenter.newCourseList.do?";

    /**
     * 分页条数
     */
    int pageSize = 10;
}
