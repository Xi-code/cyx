package com.cyx.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Title: ServletA
 * @Author 曦
 * @Date 2025/5/19 15:37
 * @description:
 */

@WebServlet("/servletA")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建Cookie
        Cookie cookie1 = new Cookie("keyA","valueA");
        Cookie cookie2 = new Cookie("keyB","valueB");
        //将Cookie放入response对象
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
    }
}
