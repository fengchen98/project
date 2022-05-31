package com.shenzhou.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzhou.logistics.entity.FreightEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 运费报价
 * @Date: 2022/1/25
 * @Time: 21:30
 **/
@Mapper
public interface FreightDao extends BaseMapper<FreightEntity> {

//    public List<FreightEntity> ListFreight();
}
