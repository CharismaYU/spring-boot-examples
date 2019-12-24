package com.neo.controller;

import com.neo.Constants;
import com.neo.annotation.SysLog;
import com.neo.domain.ApiResponse;
import com.neo.model.User;
import com.neo.service.UserService;
import com.neo.util.ShiroUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * @author :  yuxuenan 2019年12月24日
 */
@Controller(Constants.API_BEAN_NAME_PREFIX + "LoginController")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping({"/login"})
    public String login(HttpServletRequest request) {
        return "login";
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        // request.setAttribute("categoryCount", categoryService.getTotalCategories());
        // request.setAttribute("blogCount", blogService.getTotalBlogs());
        //request.setAttribute("linkCount", linkService.getTotalLinks());
        // request.setAttribute("tagCount", tagService.getTotalTags());
        //request.setAttribute("commentCount", commentService.getTotalComments());
        //  request.setAttribute("path", "index");
        return "index";
    }

    /**
     * 登录
     */
    @SysLog("登录")
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode, HttpSession session) throws IOException {
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "/login";
        }
        User user = userService.findByNameAndPassWord(userName, password);
        if (user != null) {
            session.setAttribute(Constants.CURRENT_USER, user.getNickName());
            session.setAttribute(Constants.CURRENT_USER_ID, user.getId());
            //session过期时间设置为3600秒 即一小时
            session.setMaxInactiveInterval(60 * 60 * 1);
            return "redirect:/index";
        } else {
            session.setAttribute("errorMsg", "登录失败");
            return "/login";
        }
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