package com.example.demouser.service;

import com.example.demouser.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    /**
     * 根据账号查询用户
     *
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {
        User user = new User();
        user.setId("1001");
        user.setName("accp");
        user.setPassword("123456");
        user.setEmail("accp@163.com");
        return user;
    }

}
