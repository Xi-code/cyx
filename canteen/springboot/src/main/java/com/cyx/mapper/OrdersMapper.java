package com.cyx.mapper;

import com.cyx.entity.Orders;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Title: OrdersMaper
 * @Author 曦
 * @Date 2025/6/3 22:18
 * @description:
 */
@Mapper
public interface OrdersMapper {

    void insert(Orders orders);

    void deleteById(Integer id);

    void updateById(Orders orders);

    @Select("select * from orders where id = #{id}")
    Orders selectById(Integer id);

    List<Orders> selectAll(String userName,Integer userId);

    @MapKey("date")
    List<Map<String, Object>> selectOrderStatistics();




}
