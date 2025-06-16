package com.cyx.service;

import com.cyx.entity.Orders;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Title: OrdersService
 * @Author 曦
 * @Date 2025/6/5 18:04
 * @description:
 */
@Service
public interface OrdersService {
    void add(Orders orders);
    void deleteById(Integer id);
    void deleteBatch(List<Integer> ids);

    void updateById(Orders orders);

    Orders selectById(Integer id);

    List<Orders> selectAll(@Param("userName")String name, @Param("userId") Integer userId);

    List<Map<String, Object>> getOrderStatistics();

}
