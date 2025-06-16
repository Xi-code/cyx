package com.cyx.homework4.service;

import com.cyx.homework4.mapper.UserMapper;
import com.cyx.homework4.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: UserServiceImpl
 * @Author 曦
 * @Date 2025/5/27 17:29
 * @description:
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User validateUser(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, password);
    }
}
