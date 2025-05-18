package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: User
 * @Author 曦
 * @Date 2025/3/25 19:41
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id; //ID
    private String username; //用户名
    private String password; //密码
    private String name; //姓名
    private Integer age; //年龄

    //重写toString方法
    @Override
    public String toString() {
        return "User(" +"id=" + id + "," + "username='" + username + '\'' + ", password='" + password + '\'' + ", name='" + name + '\'' + ", age=" + age + ')';
    }
}
