//package com.shenzhou.logistics.controller;
//
//import com.shenzhou.logistics.dao.NewsContentMapper;
//import com.shenzhou.logistics.entity.NewsContent;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.Serializable;
//import java.util.List;
//
////新闻内容
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/webapp/news")
//public class NewsContentController {
//        @Autowired
//        NewsContentMapper mapper;
//
//        @GetMapping("/getinfo")//返回表中所有数据（查）
//        public List<com.shenzhou.logistics.entity.NewsContent> getNewsContent() {
//            return mapper.selectList(null);
//        }
//
//        @RequestMapping("/add")
//        public Boolean saveNewsContent(@RequestBody NewsContent newscontent) {
//            //通过传来的用户信息插入用户（增）
//            int i = mapper.insert(newscontent);
//            return i == 1;
//        }
//
//        @RequestMapping("/update")
//        public Boolean updateNews(@RequestBody NewsContent newscontent) {
//            int update = mapper.updateById(newscontent);
//            return update == 1;
//        }
//
//        @RequestMapping("/delete")
//        public Boolean deleteNews(@RequestBody Serializable id) {
//            int delete = mapper.deleteById(id);
//            System.out.println("^^^^^^^^^^^^^");
//            return delete == 1;
//        }
//    }
//
//
