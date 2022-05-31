package com.shenzhou.logistics.controller;


import com.shenzhou.logistics.dao.MailContentMapper;
import com.shenzhou.logistics.entity.MailContent;
import com.shenzhou.logistics.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

//直邮内容
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/directmail")
public class MailContentController {
    @Autowired
    MailContentMapper mapper;

    @GetMapping("/getinfo")//返回表中所有数据（查）
    public R getMailContent(){
        return R.ok().put("data",mapper.selectList(null));
    }
//    public List<MailContent> getMailContent(){
//        return mapper.selectList(null);
//    }

    @RequestMapping("/add")
    public Boolean saveMailContent(@RequestBody MailContent mailcontent){
        //通过传来的用户信息插入用户（增）
        int i = mapper.insert(mailcontent);
        return i == 1;
    }

    @RequestMapping("/update")
    public Boolean updatePhoto(@RequestBody MailContent mailcontent){
        int update = mapper.updateById(mailcontent);
        return update == 1;
    }

    @RequestMapping("/delete")
    public Boolean deletePhoto(@RequestBody Serializable id){
        int delete = mapper.deleteById(id);
        System.out.println("^^^^^^^^^^^^^");
        return delete == 1;
    }


}
