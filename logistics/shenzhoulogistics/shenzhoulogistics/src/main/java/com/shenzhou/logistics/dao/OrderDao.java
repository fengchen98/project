package com.shenzhou.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzhou.logistics.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName OrderDao
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/13 12:26
 * @Version 1.0
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
}
