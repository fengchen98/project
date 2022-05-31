package com.shenzhou.logistics.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.logistics.dao.OrderDao;
import com.shenzhou.logistics.entity.OrderEntity;
import com.shenzhou.logistics.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/13 12:27
 * @Version 1.0
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<OrderEntity> queryUserOrders(String userid) {
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userid);
        return orderDao.selectList(queryWrapper);
    }
}
