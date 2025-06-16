package com.cyx.mapper;
import com.cyx.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Title: AdminMapper
 * @Author  曦
 * @Date  2025/6/4 12:30
 * @description: 
*/


@Mapper
public interface AdminMapper {
    @Select("select id, username, password, name, avatar, role from admin where username = #{username}")
    Admin selectByUsername(String username);

    void insert(Admin admin);

    void deleteById(Integer id);

    void updateById(Admin admin);

    @Select("select id, username, password, name, avatar, role from admin where id = #{id}")
    Admin selectById(Integer id);

    //@Select("select id, username, password, name, avatar, role from admin where name like concat('%',#{name},'%') order by id desc")
    List<Admin> selectAll(String name);
}
