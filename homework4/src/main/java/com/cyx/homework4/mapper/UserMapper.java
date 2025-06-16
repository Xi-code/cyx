package com.cyx.homework4.mapper;

import com.cyx.homework4.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Title: UserMapper
 * @Author 曦
 * @Date 2025/5/27 17:15
 * @description:
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    User findByUsernameAndPassword(String username, String password);
}
