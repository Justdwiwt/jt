package com.jt.manage.sso.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jt.common.redis.RedisService;
import com.jt.common.util.ObjectUtil;
import com.jt.common.vo.SysResult;
import com.jt.manage.sso.mapper.UserMapper;
import com.jt.manage.sso.pojo.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    public Boolean check(String param, Integer type) {

        int count = 0;
        User user = new User();
        if (type == 1) {
            //查到的赋值true,查不到赋值false
            user.setUsername(param);
            count = userMapper.selectCount(user);
        }
        if (type == 2) {
            user.setPhone(param);
            count = userMapper.selectCount(user);
        }
        if (type == 3) {
            user.setEmail(param);
            count = userMapper.selectCount(user);
        }
        if (count == 0) {

            return false;
        } else {
            return true;
        }
    }

    public void saveUser(User user) {
        //补充完成所有的字段属性
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());
        //密码加密
        String password = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(password);
        userMapper.insert(user);
    }

    //用户登录逻辑
    public String login(String username, String password) throws Exception {
        String ticket = "";
        //判断数据库是否有username，password同时对应的用户user对象
        User _user = new User();//封装username,password，
        //selectOne:where username and pasword
        _user.setUsername(username);
        password = DigestUtils.md5Hex(password);
        _user.setPassword(password);
        //select * from tb_user where username=** and password =**
        User user = userMapper.selectOne(_user);
        if (user == null) {//没查到，登录失败
            return ticket;
        } else {//查到用户，
            //生成ticket，利用md5加密 “JT_TICKET”+current+username
            ticket = DigestUtils.md5Hex("JT_TICKET" +
                    System.currentTimeMillis() + username);//相当于存储到redis中的唯一key
            //设定好了
            //将user转化成json作为value存到redis中
            String userJson = ObjectUtil.mapper.writeValueAsString(user);
            redisService.set(ticket, userJson, 60 * 60);
            return ticket;
        }
    }

    public String checkUserJson(String ticket, String callback) throws JsonProcessingException {
        String userJson = redisService.get(ticket);
        if (callback == null) {//需要返回json字符串
            return ObjectUtil.mapper.
                    writeValueAsString(SysResult.oK(userJson));
        } else {
            String jsonData = ObjectUtil.mapper.
                    writeValueAsString(SysResult.oK(userJson));
            return callback + "(" + jsonData + ")";
        }
    }
}





















