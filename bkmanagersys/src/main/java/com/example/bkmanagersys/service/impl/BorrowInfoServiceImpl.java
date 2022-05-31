package com.example.bkmanagersys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bkmanagersys.mapper.BorrowInfoMapper;
import com.example.bkmanagersys.pojo.Book;
import com.example.bkmanagersys.pojo.BorrowInfo;
import com.example.bkmanagersys.service.BorrowInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author FengChen
 * @description:
 * @date 2022/4/16 21:31
 * @version：1.0
 */
@Service("borrowInfoServiceImpl")
@Transactional
public class BorrowInfoServiceImpl extends ServiceImpl<BorrowInfoMapper, BorrowInfo> implements BorrowInfoService {
    @Override
    public List<BorrowInfo> getBorrowInfoList(String venue) {
        QueryWrapper<BorrowInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("stadiumstype",venue);

        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<BorrowInfo> getBorrowInfoByOpr(Page<BorrowInfo> pageParam, String userid, String idnumber, String tel, String title, String author,String venue) {
        QueryWrapper<BorrowInfo> queryWrapper=new QueryWrapper<>();
        if (!StringUtils.isEmpty(userid)) {
            queryWrapper.like("userid",userid);
        }
        if (!StringUtils.isEmpty(idnumber)) {
            queryWrapper.like("idnumber",idnumber);
        }
        if (!StringUtils.isEmpty(tel)) {
            queryWrapper.like("tel",tel);
        }
        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(author)) {
            queryWrapper.like("author",author);
        }
        queryWrapper.eq("stadiumstype",venue);
        queryWrapper.orderByDesc("id");

        Page<BorrowInfo> page = baseMapper.selectPage(pageParam, queryWrapper);

        return page;
    }
}
