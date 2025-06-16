package com.cyx.homework4.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: User
 * @Author 曦
 * @Date 2025/5/27 17:14
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String fullname;
    private String email;
}

