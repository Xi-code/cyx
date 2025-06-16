package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Title: EmpMapper
 * @Author 曦
 * @Date 2025/5/22 22:20
 * @description:
 */
/*
员工信息
 */
@Mapper
public interface EmpMapper {
    //查询总记录数
    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id;")
    public Long count();

    //分页查询 把部门名称重命名为deptName(Emp中已创建) 就可以封装到Emp对象中了
    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
            "order by e.update_time desc limit #{start},#{pageSize};")
    public List<Emp> list(Integer start,Integer pageSize);
}
