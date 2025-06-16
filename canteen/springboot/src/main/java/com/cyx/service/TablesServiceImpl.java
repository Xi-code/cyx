package com.cyx.service;

import cn.hutool.core.util.ObjectUtil;
import com.cyx.common.RoleEnum;
import com.cyx.entity.Tables;
import com.cyx.entity.Tables;
import com.cyx.exception.CustomException;
import com.cyx.mapper.TablesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @Title: TablesServiceImpl
 * @Author 曦
 * @Date 2025/6/5 18:05
 * @description:
 */
@Service
public class TablesServiceImpl implements TablesService{

    @Autowired
    private TablesMapper tablesMapper;

    @Override
    public void add(Tables tables) {
        tablesMapper.insert(tables);
    }

    @Override
    public void deleteById(Integer id) {
        tablesMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public void updateById(Tables tables) {
        if ("是".equals(tables.getFree())) {
            tables.setUserId(null);  // 清除占用的顾客信息
        }
        tablesMapper.updateById(tables);
    }

    @Override
    public Tables selectById(Integer id) {
        return tablesMapper.selectById(id);
    }

    @Override
    public List<Tables> selectAll(String name) {
        return tablesMapper.selectAll(name);
    }


    @Override
    public PageInfo<Tables> selectPage(String no, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tables> list = this.selectAll(no);
        return PageInfo.of(list);
    }

    @Override
    public void addOrder(Tables tables) {
        // 确保该用户没有占用其他餐桌
        Tables dbTables = tablesMapper.selectByUserId(tables.getUserId());
        if(dbTables != null && !Objects.equals(dbTables.getId(), tables.getId())){
            throw new CustomException("您已经预定了其他餐桌");
        }
        // 确保正确设置字段
        tables.setFree("否");
        this.updateById(tables);
    }


    @Override
    public Tables selectByUserId(Integer userId) {
        return tablesMapper.selectByUserId(userId);
    }
    @Override
    public void removeOrder(Tables tables) {
        tablesMapper.removeOrder(tables.getId());
    }
}
