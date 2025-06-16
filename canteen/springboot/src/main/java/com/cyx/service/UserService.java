package com.cyx.service;

import com.cyx.entity.Account;
import com.cyx.entity.User;
import com.cyx.entity.User;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: UserService
 * @Author 曦
 * @Date 2025/6/3 22:05
 * @description:
 */
@Service
public interface UserService {
    Account login(Account account);

    void register(User user);

    void add(User user);
    
    void deleteById(Integer id);

    void deleteBatch(List<Integer> ids);

    void updateById(User user);

    User selectById(Integer id);

    List<User> selectAll(String name);

    PageInfo<User> selectPage(String name, Integer pageNum, Integer pageSize);
}
