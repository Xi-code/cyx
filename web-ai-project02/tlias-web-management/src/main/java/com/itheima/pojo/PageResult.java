package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Title: PageResult
 * @Author 曦
 * @Date 2025/5/22 22:53
 * @description:
 */
//分页结果封装类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private Long total;//总记录数
    private List<T> rows;//记录数据
}
