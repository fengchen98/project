//package com.shenzhou.logistics.controller;
//
//
//import com.shenzhou.logistics.dao.NewsListMapper;
//import com.shenzhou.logistics.entity.NewsList;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//
////新闻列表
//@CrossOrigin(origins = "*")
//    @RestController
//    @RequestMapping("/webapp/news")
//    public class NewsListController {
//        @Autowired
//        NewsListMapper mapper;
//        @GetMapping("/list")//返回表中所有数据（查）
//        public List<NewsList> getNewsList() {
//            return mapper.selectList(null);
//        }
//    }
//
