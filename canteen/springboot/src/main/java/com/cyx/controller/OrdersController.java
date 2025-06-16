package com.cyx.controller;

import com.cyx.common.Result;
import com.cyx.entity.Orders;
import com.cyx.service.OrdersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Title: OrdersController
 * @Author 曦
 * @Date 2025/6/6 20:51
 * @description:
 */

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Orders orders) {
        ordersService.add(orders);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        ordersService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        ordersService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Orders orders) {
        ordersService.updateById(orders);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Orders orders = ordersService.selectById(id);
        if (orders == null) {
            return Result.error("订单不存在");
        }
        return Result.success(orders);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(@RequestParam(required = false) String userName,Integer userId,
                            @RequestParam int pageNum,
                            @RequestParam int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list = ordersService.selectAll(userName,userId);
        PageInfo<Orders> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo);
    }

    @GetMapping("/statistics")
    public Result getOrderStatistics() {
        List<Map<String, Object>> data = ordersService.getOrderStatistics();
        return Result.success(data);
    }

}
