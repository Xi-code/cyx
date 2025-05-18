package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.Dept;

import java.util.List;

/**
 * @Title: ClazzService
 * @Author  曦
 * @Date  2025/5/12 14:52
 * @description:
*/
public interface ClazzService {

    void add(Clazz clazz);

    List<Clazz> findAll();
}
