package com.neo.controller.study;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.neo.Constants;
import com.neo.annotation.SysLog;
import com.neo.domain.ApiResponse;
import com.neo.model.Course;
import com.neo.service.CourseService;
import com.neo.util.HttpUtil;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 课程
 *
 * @author :  yuxuenan 2019年12月31日
 */
@Controller(Constants.API_BEAN_NAME_PREFIX + "CourseController")
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @SysLog("抓取保存课程数据")
    @RequestMapping(value = "/getData", method = {RequestMethod.POST, RequestMethod.GET})
    public ApiResponse getData() {
        int PAGE_SIZE = 100;
        int PAGE_NO = 1;
        Map<String, String> mapGet = ImmutableMap.of("page.pageNo", Integer.toString(PAGE_NO), "page.pageSize", Integer.toString(PAGE_SIZE));
        while (true) {
            String newCourseList = HttpUtil.URLGet(Constants.URL_NEW_COURSELIST, mapGet, HttpUtil.URL_PARAM_DECODECHARSET_UTF8);
            Map<String, Object> mapCourse = (Map<String, Object>) JSON.parse(newCourseList);
            int pageNo = MapUtils.getIntValue(mapCourse, "pageNo");
            int totalPages = MapUtils.getIntValue(mapCourse, "totalPages");
            int nextPage = MapUtils.getIntValue(mapCourse, "nextPage");
            List<Map<String, Object>> rows = (List<Map<String, Object>>) MapUtils.getObject(mapCourse, "rows");
            for (Map<String, Object> map : rows) {
                Course course = new Course();
                course.setCourseId(MapUtils.getString(map, "courseId"));
                course.setCourseCode(MapUtils.getString(map, "courseCode"));
                course.setCourseTitle(MapUtils.getString(map, "courseTitle"));
                course.setUserFinish(MapUtils.getBoolean(map, "userFinish", false));
                courseService.save(course);
            }
            if (nextPage > totalPages) {
                break;
            }
            mapGet.put("page.pageNo", Integer.toString(pageNo + PAGE_SIZE));
        }
        return ApiResponse.success("获取成功！");
    }
}
