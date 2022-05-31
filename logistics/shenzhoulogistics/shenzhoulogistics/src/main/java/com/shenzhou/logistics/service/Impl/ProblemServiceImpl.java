package com.shenzhou.logistics.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.logistics.dao.ProblemDao;
import com.shenzhou.logistics.entity.ProblemEntity;
import com.shenzhou.logistics.service.ProblemService;
import com.shenzhou.logistics.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName ProblemServiceImpl
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/24 14:30
 * @Version 1.0
 */
@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemDao, ProblemEntity> implements ProblemService {

    @Autowired
    private ProblemDao problemDao;

    @Override
    public ProblemEntity queryProblem(String id) {
        QueryWrapper<ProblemEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("id", id)
                .eq("status", 1)
                .eq("is_delete", 0);
        return problemDao.selectOne(queryWrapper);
    }

    @Override
    public List<ProblemEntity> titleList() {
        QueryWrapper<ProblemEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("id", "type", "title")
                .eq("status", 1)
                .eq("is_delete", 0);
        return problemDao.selectList(queryWrapper);
    }

    @Override
    public List<ProblemEntity> List() {
        QueryWrapper<ProblemEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("status", 1)
                .eq("is_delete", 0);
        return problemDao.selectList(queryWrapper);
    }

    @Override
    public List<ProblemEntity> allList() {
        QueryWrapper<ProblemEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("is_delete", 0);
        return problemDao.selectList(queryWrapper);
    }

    @Override
    public List<ProblemEntity> ListByType(String type) {
        QueryWrapper<ProblemEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("is_delete", 0)
                .eq("type", type);
        return problemDao.selectList(queryWrapper);
    }

    @Override
    public boolean changeStatus(String type, String id) {
        UpdateWrapper<ProblemEntity> updateWrapper = new UpdateWrapper<>();
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
        problemDao.update(null, updateWrapper);
        return true;
    }


    @Override
    public boolean remove(String id) {
        UpdateWrapper<ProblemEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .eq("id", id)
                .set("is_delete", 1);
        problemDao.update(null, updateWrapper);
        return true;
    }
}
