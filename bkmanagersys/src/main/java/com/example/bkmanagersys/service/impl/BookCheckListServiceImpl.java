package com.example.bkmanagersys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bkmanagersys.mapper.BookCheckListMapper;
import com.example.bkmanagersys.pojo.ActivityCheckList;
import com.example.bkmanagersys.pojo.BookCheckList;
import com.example.bkmanagersys.service.BookCheckListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author FengChen
 * @description:
 * @date 2022/4/16 20:58
 * @version：1.0
 */
@Service("bookCheckListServiceImpl")
@Transactional
public class BookCheckListServiceImpl extends ServiceImpl<BookCheckListMapper, BookCheckList>implements BookCheckListService {

    @Override
    public List<BookCheckList> getBookCheckList(String vunue) {
        QueryWrapper<BookCheckList> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("status",0);
        queryWrapper.eq("stadiumstype",vunue);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<BookCheckList> getRejectList(String venue) {
        QueryWrapper<BookCheckList> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("status",2);
        queryWrapper.eq("stadiumstype",venue);
        return baseMapper.selectList(queryWrapper);
    }
}
