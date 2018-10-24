package com.jt.manage.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    //动态访问页面
    @RequestMapping("/page/{pageName}")
    public String goPages(@PathVariable String pageName) {
        return pageName;
    }
}
