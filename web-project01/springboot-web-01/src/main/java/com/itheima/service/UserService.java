package com.itheima.service;

import com.itheima.pojo.User;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Title: UserService
 * @Author 曦
 * @Date 2025/3/19 20:56
 * @description:
 */
public interface UserService {
    //查询所有用户信息
    public List<User> findAll();
}
