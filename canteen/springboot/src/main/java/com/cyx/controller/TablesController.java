package com.cyx.controller;

import com.cyx.common.Result;
import com.cyx.entity.Tables;
import com.cyx.service.TablesService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: TableController
 * @Author 曦
 * @Date 2025/6/5 18:01
 * @description:
 */

@RestController
@RequestMapping("/tables")
public class TablesController {

    @Autowired
    private TablesService tablesService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Tables tables) {
        tablesService.add(tables);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        tablesService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        tablesService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Tables tables) {
        tablesService.updateById(tables);
        return Result.success();
    }

    @PutMapping("/addOrder")
    public Result addOrder(@RequestBody Tables tables) {
        tables.setUserId(tables.getUserId());
        tablesService.addOrder(tables);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Tables tables = tablesService.selectById(id);
        return Result.success(tables);
    }

    /**
     * 根据用户id查询
     */
    @GetMapping("/selectByUserId/{userId}")
    public Result selectByUserId(@PathVariable Integer userId) {
        Tables tables = tablesService.selectByUserId(userId);
        return Result.success(tables);
    }


    @PutMapping("/removeOrder")
    public Result removeOrder(@RequestBody Tables tables) {
        tablesService.removeOrder(tables);
        return Result.success();
    }



    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(String name) {
        List<Tables> list = tablesService.selectAll(name);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            String no,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Tables> pageInfo = tablesService.selectPage(no, pageNum, pageSize);
        return Result.success(pageInfo);
    }

}
