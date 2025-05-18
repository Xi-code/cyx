package com.itheima;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: HelloController
 * @Author 曦
 * @Date 2025/3/19 14:49
 * @description:
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(String name){
        System.out.println("name:"+name);
        return "hello "+name;
    }
}
