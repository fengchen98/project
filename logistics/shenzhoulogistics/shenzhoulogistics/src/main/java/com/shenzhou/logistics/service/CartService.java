package com.shenzhou.logistics.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.entity.CartEntity;

import java.util.List;

/**
 * @ClassName CartService
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/9 13:50
 * @Version 1.0
 */
public interface CartService extends IService<CartEntity> {

    List<CartEntity> ListById(String id);

    boolean removeById(String userid, String id);

}
