package com.jt.manage.sso.controller;

import com.jt.common.util.ObjectUtil;
import com.jt.common.vo.SysResult;
import com.jt.manage.sso.pojo.User;
import com.jt.manage.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //注册数据校验
    @RequestMapping("user/check/{param}/{type}")
    @ResponseBody
    public String check(@PathVariable String param, @PathVariable Integer type,
                        String callback) throws Exception {
        //获取查询结果,false不存在,true存在
        Boolean exists = userService.check(param, type);
        //将sysresult对象转化成json
        String jsonData = ObjectUtil.mapper.
                writeValueAsString(SysResult.oK(exists));
        //封装jsonp
        String jsonpData = callback + "(" + jsonData + ")";
        return jsonpData;
    }

    //用户注册
    @RequestMapping("user/register")
    @ResponseBody
    public SysResult doRegister(User user) {
        try {
            userService.saveUser(user);
            return SysResult.oK(user.getUsername());
        } catch (Exception e) {
            return SysResult.build(201, "注册失败" + e.getMessage());
        }
    }

    //用户登录
    @RequestMapping("user/login")
    @ResponseBody
    public SysResult doLogin(@RequestParam(value = "u") String username, String p) throws Exception {
        //u表示用户名，p表示密码
        String ticket = userService.login(username, p);
        return SysResult.oK(ticket); //"null"
    }

    //利用ticket查询redis中的userJson
    @RequestMapping("user/query/{ticket}")
    @ResponseBody
    public String checkUserJson(@PathVariable String ticket, String callback) throws Exception {
        return userService.checkUserJson(ticket, callback);
    }
}

















