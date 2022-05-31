package com.shenzhou.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.entity.FreightEntity;
import com.shenzhou.logistics.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 运费报价
 * @Date: 2022/1/25
 * @Time: 21:31
 **/
public interface FreightService extends IService<FreightEntity> {
    PageUtils queryPageByCondition(Map<String, Object> params);


//    List<FreightEntity> listWithTree();

//    void selectFreightInfo(List<FreightEntity> freightEntity);
//    void selectFreightInfo();
//    List<FreightEntity> ListFreight();
}
