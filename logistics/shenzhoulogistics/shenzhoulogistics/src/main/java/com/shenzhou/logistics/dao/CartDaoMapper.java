package com.shenzhou.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzhou.logistics.entity.CartEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName cartDao
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/9 13:47
 * @Version 1.0
 */
@Mapper
public interface CartDaoMapper extends BaseMapper<CartEntity> {

//    List<CartEntity> ListById(String id);
}

