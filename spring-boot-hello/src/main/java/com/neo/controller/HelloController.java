package com.neo.controller;

import com.neo.domain.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /* @Value("${com.yyy.name}")
     private String name;
     @Value("${com.yyy.want}")
     private String want;*/
    @Value("${cusvar}")
    private String cusvar;

    @Autowired
    ConfigBean configBean;

    @RequestMapping("/")
    public String index() {
        String con = configBean.getName() + "," + configBean.getWant();
        con += configBean.getYearHope();
        return con;
    }

    @RequestMapping("/profile")
    public String profile() {
        return "wwww..." + cusvar;
    }
}