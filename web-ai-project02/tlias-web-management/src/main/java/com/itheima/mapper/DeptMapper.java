package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Title: DeptMapper
 * @Author 曦
 * @Date 2025/3/29 20:26
 * @description:
 */
@Mapper
public interface DeptMapper {

    //方式一：手动结果映射
   /* @Results({
            @Result(column="create_time",property = "createTime"),
            @Result(column="update_time",property = "updateTime")
    })*/
    //方式二：起别名 在SQL语句中，对不一样的列名起别名，别名和实体类属性名一致
    //@Select("select id, name, create_time as createTime, update_time as updateTime from dept order by update_time desc;")

    //基于xml映射文件
    //@Select("select id, name, create_time, update_time from dept order by update_time desc;")
    List<Dept> findAll();


    //根据Id删除部门
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);


    //添加部门  注意：插入的值要写属性名（Dept实体类中属性）
    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);


    //根据Id查询部门
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);


    //更新部门信息
    @Update("update dept set name=#{name},update_time=#{updateTime} where id = #{id}")
    void update(Dept dept);
}
