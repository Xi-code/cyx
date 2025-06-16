package com.cyx.mapper;

import com.cyx.entity.Tables;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Title: TablesMaper
 * @Author 曦
 * @Date 2025/6/3 22:18
 * @description:
 */
@Mapper
public interface TablesMapper {

    void insert(Tables tables);

    void deleteById(Integer id);

    void updateById(Tables tables);

    @Select("select id, no, unit, free from tables where id = #{id}")
    Tables selectById(Integer id);

    List<Tables> selectAll(String no);

    @Select("select * from tables where user_id = #{userId}")
    Tables selectByUserId(Integer userId);

    @Update("update tables set user_id = null, free = '是' where id = #{id}")
    void removeOrder(Integer id);
}
