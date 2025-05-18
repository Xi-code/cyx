package com.itheima.dao.impl;

import cn.hutool.core.io.IoUtil;
import com.itheima.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: UserDaoImpl
 * @Author 曦
 * @Date 2025/3/19 20:55
 * @description:
 */
@Repository //("userDao")
//@Component //将当前类交给IOC容器处理
public class UserDaoImpl implements UserDao {

    @Override
    public List<String> list() {
        //1.加载并读取user.txt文件  获取用户数据
        InputStream in = getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8,new ArrayList<>());
        return lines;
    }
}
