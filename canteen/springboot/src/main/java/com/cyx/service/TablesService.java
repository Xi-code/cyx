package com.cyx.service;

import com.cyx.entity.Tables;
import com.cyx.entity.Tables;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: TablesService
 * @Author 曦
 * @Date 2025/6/5 18:04
 * @description:
 */
@Service
public interface TablesService {
    void add(Tables tables);
    void deleteById(Integer id);
    void deleteBatch(List<Integer> ids);

    void updateById(Tables tables);

    Tables selectById(Integer id);

    List<Tables> selectAll(String name);

    PageInfo<Tables> selectPage(String no, Integer pageNum, Integer pageSize);

    void addOrder(Tables tables);

    Tables selectByUserId(Integer userId);

    void removeOrder(Tables tables);
}
