package com.shenzhou.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzhou.logistics.entity.UserEntity;
import com.shenzhou.logistics.vo.UserLoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户
 * @author jinlei
 * @date 2021-12-21 00:15:58
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
    UserEntity findByUsername(@Param("username") String username);
    UserEntity findUserById(@Param("Id") String Id);
}
