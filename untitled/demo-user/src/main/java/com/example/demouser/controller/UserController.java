package com.example.demouser.controller;

import com.example.demouser.pojo.User;
import com.example.demouser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController

public class UserController {

    @Resource
    private UserService userService;
    /**
     * 对外提供接口服务，根据账号查询用户信息
     *
     * @param username
     * @return
     */
    @GetMapping(value = "user/{username}", produces= "application/json;charset=UTF-8")
   // @GetMapping(value = "user/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }
}
