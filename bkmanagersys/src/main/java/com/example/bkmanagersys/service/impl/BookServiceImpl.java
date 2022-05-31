package com.example.bkmanagersys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bkmanagersys.mapper.BookMapper;
import com.example.bkmanagersys.pojo.Activity;
import com.example.bkmanagersys.pojo.Book;
import com.example.bkmanagersys.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author FengChen
 * @description:
 * @date 2022/4/16 20:50
 * @version：1.0
 */
@Service("bookServiceImpl")
@Transactional
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<Book> getBookList(String venue) {
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("stadiumstype",venue);

        return bookMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Book> getBookByOpr(Page<Book> pageParam, String title, String author,String venue) {
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(author)) {
            queryWrapper.like("author",author);
        }
        queryWrapper.eq("stadiumstype",venue);
        queryWrapper.orderByDesc("id");

        Page<Book> page = baseMapper.selectPage(pageParam, queryWrapper);

        return page;
    }
}
