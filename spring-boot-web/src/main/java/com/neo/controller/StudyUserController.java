package com.neo.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.neo.Constants;
import com.neo.annotation.SysLog;
import com.neo.domain.ApiResponse;
import com.neo.service.StudyUserService;
import com.neo.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.collections4.MapUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author :  yuxuenan 2019年12月24日
 */
@Controller(Constants.API_BEAN_NAME_PREFIX + "StudyUserController")
@RequestMapping("/study")
public class StudyUserController {

    @Autowired
    private StudyUserService studyUserService;

    /**
     * 登录
     */
    @SysLog("登录")
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResponse login(@RequestParam String username, @RequestParam String password) throws IOException {
        try {
            Map<String, Object> mapLogin = ImmutableMap.of("loginName", username, "password", password, "corpCode", "foton");

            HttpUtil.URLPost(Constants.URL_LOGIN, mapLogin, HttpUtil.URL_PARAM_DECODECHARSET_UTF8);
            Map<String, String> mapGet = ImmutableMap.of("page.pageNo", "1", "page.pageSize", "10");
            String newCourseList = HttpUtil.URLGet(Constants.URL_NEW_COURSELIST, mapGet, HttpUtil.URL_PARAM_DECODECHARSET_UTF8);
            Map<String, Object> map1 = (Map<String, Object>) JSON.parse(newCourseList);
            List<Map<String, Object>> rows = (List<Map<String, Object>>) MapUtils.getObject(map1, "rows");
            for (Map<String, Object> map : rows) {
                String courseCode = MapUtils.getString(map, "courseCode");
                String courseTitle = MapUtils.getString(map, "courseTitle");
                String courseId = MapUtils.getString(map, "courseId");
                boolean userFinish = MapUtils.getBoolean(map, "userFinish");
                System.out.println(courseCode);
            }
        } catch (Exception e) {
            return ApiResponse.error("账户验证失败");
        }
        return ApiResponse.success("");
    }


}
