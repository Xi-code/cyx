package com.cyx.mapper;

import com.cyx.entity.Foods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Title: FoodsMaper
 * @Author 曦
 * @Date 2025/6/3 22:18
 * @description:
 */
@Mapper
public interface FoodsMapper {

    void insert(Foods foods);

    void deleteById(Integer id);

    void updateById(Foods foods);

    @Select("select id, name, descr, price, img from foods where id = #{id}")
    Foods selectById(Integer id);

    List<Foods> selectAll(String name);

    Foods selectByName(String name);

    List<Foods> selectByCondition(String name, BigDecimal minPrice, BigDecimal maxPrice);
}
