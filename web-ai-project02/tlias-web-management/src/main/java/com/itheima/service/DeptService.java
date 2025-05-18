package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

/**
 * @Title: DeptService
 * @Author 曦
 * @Date 2025/3/29 20:27
 * @description:
 */
public interface DeptService {
    //查询所有的部门数据
    List<Dept> findAll();

    void deleteById(Integer id);

    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
