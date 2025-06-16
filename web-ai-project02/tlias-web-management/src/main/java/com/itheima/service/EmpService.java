package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;

/**
 * @Title: EmpService
 * @Author 曦
 * @Date 2025/5/22 22:22
 * @description:
 */
public interface EmpService {
    //分页查询 page页码 pageSize每页显示条数
    PageResult<Emp> page(Integer page, Integer pageSize);
}
