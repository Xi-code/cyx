package com.cyx.homework.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: LoginController
 * @Author 曦
 * @Date 2025/5/27 14:25
 * @description:
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // 允许跨域
public class LoginController {

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        Map<String, String> response = new HashMap<>();

        if ("admin".equals(username) && "123456".equals(password)) {
            response.put("status", "OK");
        } else {
            response.put("status", "NG");
        }
        return response;
    }
}
