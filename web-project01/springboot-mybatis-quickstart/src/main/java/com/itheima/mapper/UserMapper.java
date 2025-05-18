package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Title: UserMapper
 * @Author 曦
 * @Date 2025/3/25 19:53
 * @description:
 */

@Mapper//应用程序在运行时，会自动的为该接口创建一个实现类，这个实现类就是代理对象，并且会自动将该实现类存入IOC容器 -bean
public interface UserMapper {
    /*查询所有用户信息*/
   //@Select("select id, username, password, name, age from user")
    public List<User> findAll();

    /*
    * 根据id删除用户
    * */
    @Delete("delete from user where id=#{id}")
    public Integer deleteById(Integer id);

    /*插入用户信息*/
    @Insert("insert into user(username, password, name, age) values(#{username}, #{password}, #{name}, #{age})")
    public void insert(User user);

    /*根据id更新用户信息*/
    @Update("update user set username=#{username}, password=#{password}, name=#{name}, age=#{age} where id=#{id}")
    public void update(User user);

    /*根据用户名和密码查询用户信息*/
    @Select("select * from user where username = #{username} and password=#{password}")
    //public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    //基于springboot官方框架创建的 可以省略@Param注解
    public User findByUsernameAndPassword(String username,String password);

}
