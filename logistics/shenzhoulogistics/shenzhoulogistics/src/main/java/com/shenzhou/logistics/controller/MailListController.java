package com.shenzhou.logistics.controller;


import com.shenzhou.logistics.dao.MailListMapper;
import com.shenzhou.logistics.entity.MailList;
import com.shenzhou.logistics.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//直邮列表
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/directmail")
public class MailListController {
        @Autowired
        MailListMapper mapper;
        @GetMapping("/list")//返回表中所有数据（查）
         public R getMailList(){
            return R.ok().put("data",mapper.selectList(null));
         }
//        public List<MailList> getMailList(){
//            return mapper.selectList(null);
//        }
}
