package com.shenzhou.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzhou.logistics.entity.CarouselEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//继承BaseMapper相当于帮自己写好了通用的增删改查方法，mybatis-plus官网有详解
public interface CarouselDao extends BaseMapper<CarouselEntity> {
}
