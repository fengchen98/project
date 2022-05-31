package com.shenzhou.logistics.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.logistics.dao.OrderDetailDao;
import com.shenzhou.logistics.entity.OrderDetailEntity;
import com.shenzhou.logistics.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OrderDetailServiceImpl
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/13 15:01
 * @Version 1.0
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailDao, OrderDetailEntity> implements OrderDetailService {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public List<OrderDetailEntity> getListByOrderNumber(String order_number) {
        QueryWrapper<OrderDetailEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_number", order_number);
        return orderDetailDao.selectList(queryWrapper);
    }
}
