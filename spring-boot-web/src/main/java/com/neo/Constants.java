package com.neo;

/**
 * @author :  yuxuenan 2019年12月24日
 */
public interface Constants {

    /**
     * 当前登录用户
     */
    String CURRENT_USER = "curUser";

    /**
     * 当前登录用户
     */
    String CURRENT_USER_ID = "curUserId";

    /** Spring Bean前缀 */
    String API_BEAN_NAME_PREFIX = "api";

    /**验证码的生成文本集合 */
    String KAPTCHA_TEXT = "abcde2345678fghkynmpwx";

    /**
     * 上传文件的默认url前缀，根据部署设置自行修改
     */
    String FILE_UPLOAD_DIC = "/opt/deploy/upload/";

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
