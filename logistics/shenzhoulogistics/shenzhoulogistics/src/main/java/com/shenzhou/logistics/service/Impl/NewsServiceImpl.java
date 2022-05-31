package com.shenzhou.logistics.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.logistics.dao.NewsDao;
import com.shenzhou.logistics.entity.NewsEntity;
import com.shenzhou.logistics.entity.ProblemEntity;
import com.shenzhou.logistics.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName NewsServiceImpl
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/25 17:21
 * @Version 1.0
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsDao, NewsEntity> implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public NewsEntity queryNews(String id) {
        QueryWrapper<NewsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("id", id)
                .eq("status", 1)
                .eq("is_delete", 0);
        return newsDao.selectOne(queryWrapper);
    }

    @Override
    public List<NewsEntity> titleList() {
        QueryWrapper<NewsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("id", "username", "image","type", "title", "description", "create_time")
                .eq("status", 1)
                .eq("is_delete", 0);
        return newsDao.selectList(queryWrapper);
    }

    @Override
    public List<NewsEntity> titleListByType(String type) {
        QueryWrapper<NewsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("id", "username", "image", "type", "title", "description", "create_time")
                .eq("status", 1)
                .eq("is_delete", 0)
                .eq("type", type);
        return newsDao.selectList(queryWrapper);
    }

    @Override
    public List<NewsEntity> List() {
        QueryWrapper<NewsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("status", 1)
                .eq("is_delete", 0);
        return newsDao.selectList(queryWrapper);
    }

    @Override
    public List<NewsEntity> allList() {
        QueryWrapper<NewsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("is_delete", 0);
        return newsDao.selectList(queryWrapper);
    }

    @Override
    public List<NewsEntity> ListByType(String type) {
        QueryWrapper<NewsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("is_delete", 0)
                .eq("type", type);
        return newsDao.selectList(queryWrapper);
    }

    @Override
    public boolean changeStatus(String type, String id) {
        UpdateWrapper<NewsEntity> updateWrapper = new UpdateWrapper<>();
        if(Objects.equals(type, "online")){
            updateWrapper
                    .eq("id", id)
                    .set("status", 1);
        }
        if(Objects.equals(type, "offline")){
            updateWrapper
                    .eq("id", id)
                    .set("status", 0);
        }
        newsDao.update(null, updateWrapper);
        return true;
    }

    @Override
    public boolean remove(String id) {
        UpdateWrapper<NewsEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .eq("id", id)
                .set("is_delete", 1);
        newsDao.update(null, updateWrapper);
        return true;
    }

}
