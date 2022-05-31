package com.shenzhou.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.entity.OrderEntity;

import java.util.List;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/13 12:26
 * @Version 1.0
 */
public interface OrderService extends IService<OrderEntity> {

    List<OrderEntity> queryUserOrders(String userid);
}
