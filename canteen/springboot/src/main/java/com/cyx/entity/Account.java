package com.cyx.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Title: Account
 * @Author 曦
 * @Date 2025/6/3 21:44
 * @description:用户和管理员的基类
 */
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String avatar;
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }



}
