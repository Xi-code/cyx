package com.cyx.service;

import cn.hutool.core.util.ObjectUtil;
import com.cyx.common.RoleEnum;
import com.cyx.entity.Account;
import com.cyx.entity.User;
import com.cyx.exception.CustomException;
import com.cyx.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


/**
 * @Title: UserServiceImpl
 * @Author 曦
 * @Date 2025/6/3 22:05
 * @description:
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public Account login(Account account) {
        String username = account.getUsername();
        //根据账号查询数据
        User dbUser = userMapper.selectByUsername(username);
        if(dbUser == null){
            throw new CustomException("账号不存在");
        }
        //校验密码
        if(!dbUser.getPassword().equals(account.getPassword())){
            throw new CustomException("账号或者密码错误");
        }
        return dbUser;//dbUser是Account的子类型，可以作为返回值
    }

    //往数据库添加数据
    @Override
    public void register(User user) {
        //校验用户账号是否存在
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if(dbUser != null){
            throw new CustomException("账号已存在");
        }
        //校验密码是否为空
        if(ObjectUtil.isEmpty(user.getPassword())){
            throw new CustomException("密码不能为空");
        }
        if(ObjectUtil.isEmpty(user.getName())){
            user.setName(user.getUsername());//设置用户名称为账号名称
        }
        if (user.getAccount() == null) {
            user.setAccount(BigDecimal.ZERO);  // 设置默认余额
        }

        user.setRole(RoleEnum.USER.name());
        //把数据插入数据库
        userMapper.insert(user);
    }

    @Override
    public void add(User user) {
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if(dbUser != null){
            throw new CustomException("账号已存在");
        }
        if(ObjectUtil.isEmpty(user.getPassword())){
            user.setPassword("123");//默认密码
        }
        if(ObjectUtil.isEmpty(user.getName())){
            user.setName(user.getUsername());//设置用户名称为账号名称
        }
        if (user.getAccount() == null) {
            user.setAccount(BigDecimal.ZERO);
        }
        user.setRole(RoleEnum.USER.name());
        userMapper.insert(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public void updateById(User user) {
        userMapper.updateById(user);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> selectAll(String name) {
        return userMapper.selectAll(name);
    }


    @Override
    public PageInfo<User> selectPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = this.selectAll(name);
        return PageInfo.of(list);
    }
}
