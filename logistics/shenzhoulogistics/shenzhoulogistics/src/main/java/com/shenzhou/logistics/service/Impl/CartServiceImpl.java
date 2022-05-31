package com.shenzhou.logistics.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.logistics.dao.CartDaoMapper;
import com.shenzhou.logistics.entity.CartEntity;
import com.shenzhou.logistics.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName CartServiceImpl
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/9 14:25
 * @Version 1.0
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartDaoMapper, CartEntity> implements CartService {

    @Autowired
    private CartDaoMapper cartDaoMapper;

    @Override
    public List<CartEntity> ListById(String id) {

        QueryWrapper<CartEntity> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(id) && !"0".equalsIgnoreCase(id)) {
            queryWrapper.eq("user_id", id);
        }

        return cartDaoMapper.selectList(queryWrapper);
    }

    @Override
    public boolean removeById(String userid, String id){
        QueryWrapper<CartEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("id", id);
        CartEntity cartEntity = cartDaoMapper.selectOne(queryWrapper);
        if(cartEntity != null){
            cartDaoMapper.deleteById(id);
        }
        return false;
    }
}
