package com.cyx.controller;

import com.cyx.common.Result;
import com.cyx.entity.Foods;
import com.cyx.service.FoodsService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Title: FoodsController
 * @Author 曦
 * @Date 2025/6/6 20:51
 * @description:
 */

@RestController
@RequestMapping("/foods")
public class FoodsController {

    @Autowired
    private FoodsService foodsService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Foods foods) {
        foodsService.add(foods);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        foodsService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        foodsService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Foods foods) {
        foodsService.updateById(foods);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Foods foods = foodsService.selectById(id);
        return Result.success(foods);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(String name) {
        List<Foods> list = foodsService.selectAll(name);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            String name,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Foods> pageInfo = foodsService.selectPage(name, minPrice, maxPrice, pageNum, pageSize);
        return Result.success(pageInfo);
    }


}
