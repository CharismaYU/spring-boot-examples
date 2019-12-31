package com.neo.controller.study;

import com.google.common.collect.ImmutableMap;
import com.neo.Constants;
import com.neo.annotation.SysLog;
import com.neo.domain.ApiResponse;
import com.neo.service.StudyUserService;
import com.neo.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
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

            String urlPost = HttpUtil.URLPost(Constants.URL_LOGIN, mapLogin, HttpUtil.URL_PARAM_DECODECHARSET_UTF8);
            if (StringUtils.isNotEmpty(urlPost)) {
                studyUserService.save(username, password);
            }
        } catch (Exception e) {
            return ApiResponse.error("账户验证失败");
        }
        return ApiResponse.success("");
    }


}
