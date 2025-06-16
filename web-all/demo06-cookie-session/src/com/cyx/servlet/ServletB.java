package com.cyx.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Title: ServletB
 * @Author 曦
 * @Date 2025/5/19 15:39
 * @description:
 */
@WebServlet("/servletB")
public class ServletB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中携带的cookie
        Cookie[] cookies = req.getCookies();
        //请求中的多个cookie会进入该数组， 请求中如果没有cookie cookie数组是null 还是长度为0?
        for(Cookie cookie : cookies){
            System.out.println(cookie.getName() + " : " + cookie.getValue());
        }
    }
}
