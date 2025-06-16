package com.cyx.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.cyx.entity.Orders;
import com.cyx.entity.User;
import com.cyx.exception.CustomException;
import com.cyx.mapper.OrdersMapper;
import com.cyx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

/**
 * @Title: OrdersServiceImpl
 * @Author 曦
 * @Date 2025/6/8 16:09
 * @description:
 */
@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(Orders orders) {
        if (orders.getTotal() == null || orders.getTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new CustomException("金额不能为负");
        }
        if (orders.getUserId() == null) {
            throw new CustomException("用户ID不能为空");
        }
        // 设置唯一的订单号
        String orderNo = IdUtil.fastSimpleUUID();
        orders.setOrderNo(orderNo);
        orders.setTime(DateUtil.now());  // 当前的年月日 时分秒
        ordersMapper.insert(orders);
    }

    @Override
    public void deleteById(Integer id) {
        Orders existing = ordersMapper.selectById(id);
        if (existing == null) {
            throw new CustomException("订单不存在");
        }
        ordersMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new CustomException("请选择要删除的订单ID");
        }
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public void updateById(Orders orders) {
        if (orders.getId() == null) {
            throw new CustomException("订单ID不能为空");
        }
        if(orders.getStatus().equals("已完成")){
            //扣减用户余额
            User user = userMapper.selectById(orders.getUserId());
            BigDecimal total = orders.getTotal();
            BigDecimal account = user.getAccount().subtract(total);
            if(account.doubleValue() < 0){
                throw new CustomException("余额不足");
            }
            user.setAccount(account);
            userMapper.updateById(user);
        }
        ordersMapper.updateById(orders);
    }
    @Override
    public Orders selectById(Integer id) {
        return ordersMapper.selectById(id);
    }

    @Override
    public List<Orders> selectAll(String userName,Integer userId) {
        return ordersMapper.selectAll(userName,userId);
    }

    @Override
    public List<Map<String, Object>> getOrderStatistics() {
        return ordersMapper.selectOrderStatistics();
    }


}
