package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import com.itheima.service.ClazzServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: ClazzController
 * @Author 曦
 * @Date 2025/5/12 14:51
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("添加班级：{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    @GetMapping
    public Result list(){
        log.info("查询全部的班级数据");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }


}
