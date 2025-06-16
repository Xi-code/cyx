package com.cyx.service;

/**
 * @Title: AdminService
 * @Author  曦
 * @Date  2025/6/3 19:57
 * @description: 
*/
import com.cyx.entity.Account;
import com.cyx.entity.Admin;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    Account login(Account account);

    void add(Admin admin);

    void deleteById(Integer id);

    void deleteBatch(List<Integer> ids);

    void updateById(Admin admin);

    Admin selectById(Integer id);

    List<Admin> selectAll(String name);

    PageInfo<Admin> selectPage(String name,Integer pageNum, Integer pageSize);
}
