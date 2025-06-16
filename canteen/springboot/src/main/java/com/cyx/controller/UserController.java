package com.cyx.controller;

import com.cyx.common.Result;
import com.cyx.entity.User;
import com.cyx.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: UserController
 * @Author  曦
 * @Date  2025/6/4 12:32
 * @description:
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    //新增
    @PostMapping("add")
    public Result add(@RequestBody User user){
        userService.add(user);
        return Result.success();
    }
    //删除
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id){
        userService.deleteById(id);
        return Result.success();
    }

    //批量删除
    @DeleteMapping("delete/batch")
    public Result delete(@RequestBody List<Integer> ids){
        userService.deleteBatch(ids);
        return Result.success();
    }

    //新增
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.updateById(user);
        return Result.success();
    }
    //查询单个
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        User user = userService.selectById(id);
        return Result.success(user);
    }

    //查询全部
    @GetMapping("/selectAll")
    public Result selectAll(String name){
        List<User> users = userService.selectAll(name);
        return Result.success(users);
    }

    //分页查询
    @GetMapping("/selectPage")
    public Result selectPage(
             String name,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<User> userPageInfo = userService.selectPage(name,pageNum, pageSize);
        return Result.success(userPageInfo);
    }

}
