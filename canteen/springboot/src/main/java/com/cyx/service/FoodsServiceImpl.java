package com.cyx.service;

import com.cyx.entity.Foods;
import com.cyx.exception.CustomException;
import com.cyx.mapper.FoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @Title: FoodsServiceImpl
 * @Author 曦
 * @Date 2025/6/5 18:05
 * @description:
 */
@Service
public class FoodsServiceImpl implements FoodsService{

    @Autowired
    private FoodsMapper foodsMapper;

    @Override
    public void add(Foods foods) {
        // 1. 验证名称是否为空
        if (foods.getName() == null || foods.getName().trim().isEmpty()) {
            throw new CustomException("菜品名称不能为空");
        }

        // 2. 验证价格是否为正数
        if (foods.getPrice() == null || foods.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new CustomException("菜品价格必须为正数");
        }

        // 3. 检查同名菜品是否已存在
        Foods existingFood = foodsMapper.selectByName(foods.getName());
        if (existingFood != null) {
            throw new CustomException("该菜品名称已存在");
        }

        // 4. 执行添加操作
        foodsMapper.insert(foods);
    }

    @Override
    public void deleteById(Integer id) {
        // 1. 验证ID是否为空
        if (id == null) {
            throw new CustomException("ID不能为空");
        }

        // 2. 检查菜品是否存在
        Foods foods = foodsMapper.selectById(id);
        if (foods == null) {
            throw new CustomException("该菜品不存在，无法删除");
        }

        // 3. 执行删除操作
        foodsMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        // 1. 验证ID列表是否为空
        if (ids == null || ids.isEmpty()) {
            throw new CustomException("删除ID列表不能为空");
        }

        // 2. 批量删除并检查每个ID
        for (Integer id : ids) {
            try {
                deleteById(id);
            } catch (CustomException e) {
                // 记录删除异常，但继续执行其他删除操作
                System.err.println("删除ID " + id + " 时出错: " + e.getMessage());
            }
        }
    }

    @Override
    public void updateById(Foods foods) {
        // 1. 验证ID是否为空
        if (foods.getId() == null) {
            throw new CustomException("更新时ID不能为空");
        }

        // 2. 检查菜品是否存在
        Foods existingFood = foodsMapper.selectById(foods.getId());
        if (existingFood == null) {
            throw new CustomException("该菜品不存在，无法更新");
        }

        // 3. 验证名称是否为空
        if (foods.getName() == null || foods.getName().trim().isEmpty()) {
            throw new CustomException("更新时菜品名称不能为空");
        }

        // 4. 验证价格是否为正数
        if (foods.getPrice() != null && foods.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new CustomException("更新时菜品价格必须为正数");
        }

        // 5. 检查同名菜品是否已存在（排除自身）
        Foods nameConflictFood = foodsMapper.selectByName(foods.getName());
        if (nameConflictFood != null && !nameConflictFood.getId().equals(foods.getId())) {
            throw new CustomException("该菜品名称已存在");
        }

        // 6. 执行更新操作
        foodsMapper.updateById(foods);
    }

    @Override
    public Foods selectById(Integer id) {
        // 验证ID是否为空
        if (id == null) {
            throw new CustomException("查询时ID不能为空");
        }

        return foodsMapper.selectById(id);
    }

    @Override
    public List<Foods> selectAll(String name) {
        return foodsMapper.selectAll(name);
    }

    @Override
    public PageInfo<Foods> selectPage(String name, BigDecimal minPrice, BigDecimal maxPrice, Integer pageNum, Integer pageSize) {
        // 1. 验证页码和页大小
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }

        // 2. 验证价格范围
        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            throw new CustomException("最小价格不能大于最大价格");
        }

        // 3. 执行分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<Foods> list = foodsMapper.selectByCondition(name, minPrice, maxPrice);
        return PageInfo.of(list);
    }
}
