package com.itheima.service;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: EmpServiceImpl
 * @Author 曦
 * @Date 2025/5/22 22:22
 * @description:
 */
@Service
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //调用mapper接口查询总记录数
        Long total = empMapper.count();

        //调用mapper接口查询结果列表
        //起始索引 = （页码-1）*每页展示的记录数
        Integer start = (page-1)*pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);
        //封装PageResult对象
        return new PageResult<Emp>(total,rows);
    }
}
