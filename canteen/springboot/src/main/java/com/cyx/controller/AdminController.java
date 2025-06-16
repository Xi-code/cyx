package com.cyx.controller;

import com.cyx.common.Result;
import com.cyx.entity.Admin;
import com.cyx.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: AdminController
 * @Author  曦
 * @Date  2025/6/4 12:32
 * @description:
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    //新增
    @PostMapping("add")
    public Result add(@RequestBody Admin admin){
        adminService.add(admin);
        return Result.success();
    }
    //删除
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id){
        adminService.deleteById(id);
        return Result.success();
    }

    //批量删除
    @DeleteMapping("delete/batch")
    public Result delete(@RequestBody List<Integer> ids){
        adminService.deleteBatch(ids);
        return Result.success();
    }

    //新增
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin){
        adminService.updateById(admin);
        return Result.success();
    }
    //查询单个
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        Admin admin = adminService.selectById(id);
        return Result.success(admin);
    }

    //查询全部
    @GetMapping("/selectAll")
    public Result selectAll(String name){
        List<Admin> admins = adminService.selectAll(name);
        return Result.success(admins);
    }

    //分页查询
    @GetMapping("/selectPage")
    public Result selectPage(
             String name,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<Admin> adminPageInfo = adminService.selectPage(name,pageNum, pageSize);
        return Result.success(adminPageInfo);

    }

}
