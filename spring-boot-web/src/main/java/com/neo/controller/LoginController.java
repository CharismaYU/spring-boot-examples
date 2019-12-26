package com.neo.controller;

import com.neo.Constants;
import com.neo.annotation.SysLog;
import com.neo.model.User;
import com.neo.service.UserService;
import com.neo.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(Constants.CURRENT_USER_ID);
        request.getSession().removeAttribute(Constants.CURRENT_USER);
        request.getSession().removeAttribute("errorMsg");
        return "login";
    }
}
