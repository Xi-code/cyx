package com.itheima;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Title: responseController
 * @Author 曦
 * @Date 2025/3/19 16:34
 * @description:
 */

@RestController
class responseController {
    @RequestMapping("/response")
    public void response(HttpServletResponse response) throws IOException {
        //1.设置相应状态码
        response.setStatus(401);
        //2.设置响应
        response.setHeader("name","itheima" );
        //3.设置响应体
        response.getWriter().write("<h1>hello response</h1>");
    }
    /*
    * 方式二：ResponseEntity-Spring中提供的方式
    */
    @RequestMapping("response2")
    public ResponseEntity<String> response2(){
        return ResponseEntity
                .status(401)
                .header("name","javaweb-ai")
                .body("<h1>hello response</h1>");
    }
}
