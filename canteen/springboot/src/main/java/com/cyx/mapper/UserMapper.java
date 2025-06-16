package com.cyx.mapper;

import com.cyx.entity.Admin;
import com.cyx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Title: UserMaper
 * @Author 曦
 * @Date 2025/6/3 22:18
 * @description:
 */
@Mapper
public interface UserMapper {
    @Select("select id, username, password, name, avatar, role, sex, phone, account from user where username = #{username}")
    User selectByUsername(String username);

    void insert(User user);
    void deleteById(Integer id);

    void updateById(User user);

    @Select("select id, username, password, name, avatar, role, sex, phone, account from user where id = #{id}")
    User selectById(Integer id);

    List<User> selectAll(String name);
}
