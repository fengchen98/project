package com.shenzhou.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.entity.UserEntity;
import com.shenzhou.logistics.vo.UserLoginVo;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description:
 * @Date: 2022/1/24
 * @Time: 20:53
 **/
public interface TokenService extends IService<UserEntity> {

    String getToken(UserEntity user);

}
