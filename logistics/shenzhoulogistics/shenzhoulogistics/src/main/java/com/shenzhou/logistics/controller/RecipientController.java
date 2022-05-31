package com.shenzhou.logistics.controller;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenzhou.logistics.entity.RecipientEntity;
import com.shenzhou.logistics.service.RecipientService;
import com.shenzhou.logistics.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName RecipientController
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/10 8:03
 * @Version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/recipient")
public class RecipientController {

    @Autowired
    private RecipientService recipientService;

    @RequestMapping("/list")
    public R ListById(HttpServletRequest httpServletRequest){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        List<RecipientEntity> data =  recipientService.ListById(userId);
        return R.ok().put("data", data);
    }

    @RequestMapping("/setDefault/{id}")
    public R setDefault(HttpServletRequest httpServletRequest, @PathVariable String id){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        boolean isDefault =  recipientService.setDefaultById(userId, id);
        if(isDefault){
            return R.ok().put("msg", "已将该地址设为默认");
        }else {
            return R.error().put("msg", "设置失败，请重试");
        }
    }

    @RequestMapping("/save")
    public R save(HttpServletRequest httpServletRequest, @RequestBody RecipientEntity recipientEntity){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        recipientEntity.setUserid(userId);
        boolean isSave = recipientService.save(recipientEntity);
        if(isSave){
            return R.ok();
        }
        return R.error("保存地址失败，请重试");
    }

    @RequestMapping("/remove/{id}")
    public R remove(HttpServletRequest httpServletRequest, @PathVariable String id){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        boolean isRemove =  recipientService.removeById(userId, id);
        if(isRemove){
            return R.ok();
        }
        return R.error("删除地址失败，请重试");
    }

    @RequestMapping("/update")
    public R update(HttpServletRequest httpServletRequest, @RequestBody RecipientEntity recipientEntity){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        boolean isUpdate =  recipientService.updateById(userId, recipientEntity);
        if(isUpdate){
            return R.ok("已修改");
        }
        return R.error("修改失败，请重试");
    }

}
