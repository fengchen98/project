package com.shenzhou.logistics.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.logistics.dao.CIDDao;
import com.shenzhou.logistics.entity.CIDEntity;
import com.shenzhou.logistics.service.CIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CIDServiceImpl
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/5/14 14:08
 * @Version 1.0
 */
@Service
public class CIDServiceImpl extends ServiceImpl<CIDDao, CIDEntity> implements CIDService {

    @Autowired
    private CIDDao cidDao;

    @Override
    public CIDEntity getByUserID(String user_id) {
        QueryWrapper<CIDEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user_id);
        return cidDao.selectOne(queryWrapper);
    }
}
