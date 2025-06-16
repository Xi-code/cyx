package com.itheima.controller;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: EmpController
 * @Author 曦
 * @Date 2025/5/22 22:25
 * @description:
 */
@RestController
@Slf4j
@RequestMapping("/emps")


public class EmpController {
    @Autowired
    private EmpService  empService;;
    //分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10")Integer pageSize){
        log.info("分页查询：{},{}",page,pageSize);
        PageResult<Emp> pageResult =  empService.page(page,pageSize);
        return Result.success(pageResult);
    }
}
