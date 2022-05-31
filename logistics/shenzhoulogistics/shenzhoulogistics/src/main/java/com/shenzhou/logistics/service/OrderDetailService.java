package com.shenzhou.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.entity.OrderDetailEntity;

import java.util.List;

/**
 * @ClassName OrderDetailService
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/13 15:00
 * @Version 1.0
 */
public interface OrderDetailService extends IService<OrderDetailEntity> {

    List<OrderDetailEntity> getListByOrderNumber(String order_number);
}
