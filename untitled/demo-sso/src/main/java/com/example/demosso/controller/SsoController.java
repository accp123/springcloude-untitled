package com.example.demosso.controller;

import com.example.demosso.pojo.OptResult;
import com.example.demosso.pojo.User;
import com.example.demosso.service.SsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RefreshScope
public class SsoController {

    @Resource
    private SsoService ssoService;

    /**
     * 登录接口
     *
     * @param username 账号
     * @param password 密码
     * @return
     */
    @GetMapping(value = "login/{username}/{password}")
    public OptResult queryItemById(@PathVariable("username") String username,
                                   @PathVariable("password") String password,
                                   HttpServletRequest request) {
        User user = ssoService.checkUser(username,password);
        if(user == null || user.getId() == null){
            return new OptResult(1,"登录失败",user);
        }
        return new OptResult(1,"登录成功",user);
    }
}
