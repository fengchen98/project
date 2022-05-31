package com.shenzhou.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzhou.logistics.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 奶粉商城
 * @Date: 2022/1/28
 * @Time: 0:08
 **/
@Mapper
public interface ProductDao extends BaseMapper<ProductEntity> {
}
