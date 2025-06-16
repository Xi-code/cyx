package com.cyx.service;

import com.cyx.entity.Foods;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Title: FoodsService
 * @Author 曦
 * @Date 2025/6/5 18:04
 * @description:
 */
@Service
public interface FoodsService {
    void add(Foods foods);
    void deleteById(Integer id);
    void deleteBatch(List<Integer> ids);

    void updateById(Foods foods);

    Foods selectById(Integer id);

    List<Foods> selectAll(String name);

    PageInfo<Foods> selectPage(String name, BigDecimal minPrice, BigDecimal maxPrice, Integer pageNum, Integer pageSize);
}
