package com.shenzhou.logistics.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.logistics.dao.ConcatDao;
import com.shenzhou.logistics.entity.ConcatEntity;
import com.shenzhou.logistics.service.ConcatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ConcatServiceImpl
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/5/13 19:45
 * @Version 1.0
 */
@Service
public class ConcatServiceImpl extends ServiceImpl<ConcatDao, ConcatEntity> implements ConcatService {

    @Autowired
    private ConcatDao concatDao;

    @Override
    public List<ConcatEntity> getList() {
        QueryWrapper<ConcatEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0).orderByDesc("create_time");
        return concatDao.selectList(queryWrapper);
    }

    @Override
    public boolean remove(String id) {
        UpdateWrapper<ConcatEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("is_delete", 1);
        concatDao.update(null, updateWrapper);
        return true;
    }

    @Override
    public boolean deal(String id) {
        UpdateWrapper<ConcatEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("status", 1);
        concatDao.update(null, updateWrapper);
        return true;
    }

    @Override
    public List<ConcatEntity> search(String text) {
        QueryWrapper<ConcatEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("tel", text)
                .or()
                .like("email", text)
                .or()
                .like("subject", text)
                .or()
                .like("content", text)
                .or()
                .like("name", text)
                .orderByDesc("create_time");
        return concatDao.selectList(queryWrapper);
    }
}
