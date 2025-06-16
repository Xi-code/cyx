package com.cyx.homework4.service;

import com.cyx.homework4.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @Title: UserService
 * @Author 曦
 * @Date 2025/5/27 17:28
 * @description:
 */

public interface UserService {
    User validateUser(String username, String password);
}
