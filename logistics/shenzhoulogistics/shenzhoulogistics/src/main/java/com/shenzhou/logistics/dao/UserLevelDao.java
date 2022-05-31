package com.shenzhou.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzhou.logistics.entity.UserLevelEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户等级
 * @author jinlei
 * @date 2021-12-21 00:15:58
 */
@Mapper
public interface UserLevelDao extends BaseMapper<UserLevelEntity> {

     UserLevelEntity getDefaultLevel();
}
