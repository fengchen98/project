package com.shenzhou.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzhou.logistics.entity.OrderDetailEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName OrderDetailDao
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/13 14:59
 * @Version 1.0
 */
@Mapper
public interface OrderDetailDao extends BaseMapper<OrderDetailEntity> {
}
