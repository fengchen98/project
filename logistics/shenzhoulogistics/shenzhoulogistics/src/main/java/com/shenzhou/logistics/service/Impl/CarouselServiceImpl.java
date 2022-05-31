package com.shenzhou.logistics.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.logistics.dao.CarouselDao;
import com.shenzhou.logistics.entity.CarouselEntity;
import com.shenzhou.logistics.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CarouselServiceImpl
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/23 19:24
 * @Version 1.0
 */
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselDao, CarouselEntity> implements CarouselService {

    @Autowired
    private CarouselDao carouselDao;

    @Override
    public List<CarouselEntity> onlineList() {
        QueryWrapper<CarouselEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0).eq("status", 1);
        return carouselDao.selectList(queryWrapper);
    }

    @Override
    public List<CarouselEntity> offlineList() {
        QueryWrapper<CarouselEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0).eq("status", 0);
        return carouselDao.selectList(queryWrapper);
    }

    @Override
    public List<CarouselEntity> allList() {
        QueryWrapper<CarouselEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0);
        return carouselDao.selectList(queryWrapper);
    }


    @Override
    public boolean online(String id) {
        UpdateWrapper<CarouselEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("status", 1);
        carouselDao.update(null, updateWrapper);
        return true;
    }

    @Override
    public boolean offline(String id) {
        UpdateWrapper<CarouselEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("status", 0);
        carouselDao.update(null, updateWrapper);
        return true;
    }

    @Override
    public boolean del(String id) {
        UpdateWrapper<CarouselEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("is_delete", 1);
        carouselDao.update(null, updateWrapper);
        return true;
    }
}
