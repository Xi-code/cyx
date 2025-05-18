package com.itheima;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: RequestController
 * @Author 曦
 * @Date 2025/3/19 15:51
 * @description:
 */

@RestController
class RequestController {

    @RequestMapping("/request")
    public String request(HttpServletRequest request) {
        //1.获取请求方式
        String method = request.getMethod();//GET
        System.out.println("请求方式: "+method);
        //2.获取请求url地址
        String url = request.getRequestURL().toString();//http://localhost:8080/request
        System.out.println("请求url: "+url);
        String uri = request.getRequestURI();
        System.out.println("请求uri: "+uri);
        //3.获取请求协议
        String protocol = request.getProtocol();//HTTP/1.1
        System.out.println("请求协议: "+protocol);
        //4.获取请求参数-name,age
        String name = request.getParameter("name");//itheima
        String age = request.getParameter("age");//18
        System.out.println("请求参数 name: "+name+"age: "+age);
        //5.获取请求头
        String accept = request.getHeader("Accept");
        System.out.println("Accept: "+accept);
        return "OK";
    }
}
