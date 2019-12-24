package com.neo.controller;

import com.neo.Constants;
import com.neo.annotation.SysLog;
import com.neo.domain.ApiResponse;
import com.neo.model.User;
import com.neo.service.UserService;
import com.neo.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author :  yuxuenan 2019年12月24日
 */
@Controller(Constants.API_BEAN_NAME_PREFIX + "LoginController")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     */
    @SysLog("登录")
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResponse login(String username, String password) throws IOException {
        try {
            User user = userService.findByNameAndPassWord(username, password);
        } catch (Exception e) {
            return ApiResponse.error("账户验证失败");
        }
        return ApiResponse.success("");
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "redirect:/";
    }
}
