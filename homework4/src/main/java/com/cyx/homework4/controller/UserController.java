package com.cyx.homework4.controller;

import com.cyx.homework4.pojo.User;
import com.cyx.homework4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: UserController
 * @Author 曦
 * @Date 2025/5/27 17:28
 * @description:
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // 允许跨域
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody User loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        User user = userService.validateUser(username, password);
        System.out.println("前端传入：" + username + " / " + password);
        System.out.println("数据库返回：" + user);
        // 返回User对象，如果为null表示登录失败
        return user;
    }
}

