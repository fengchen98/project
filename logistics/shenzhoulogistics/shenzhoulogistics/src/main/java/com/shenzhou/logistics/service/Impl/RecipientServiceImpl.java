package com.shenzhou.logistics.service.Impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.logistics.dao.RecipientDao;
import com.shenzhou.logistics.entity.RecipientEntity;
import com.shenzhou.logistics.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName RecipientServiceImpl
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/10 7:46
 * @Version 1.0
 */
@Service
public class RecipientServiceImpl extends ServiceImpl<RecipientDao, RecipientEntity> implements RecipientService {

    @Autowired
    private RecipientDao recipientDao;

    @Override
    public List<RecipientEntity> ListById(String userid) {
        QueryWrapper<RecipientEntity> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userid) && !"0".equalsIgnoreCase(userid)) {
            queryWrapper.eq("userid", userid);
        }
        return recipientDao.selectList(queryWrapper);
    }

    @Override
    public boolean setDefaultById(String userid, String id) {
        List<RecipientEntity> List = ListById(userid);
        for(RecipientEntity recipient: List){
            String currentId = recipient.getId();
            UpdateWrapper<RecipientEntity> updateWrapper = new UpdateWrapper<>();
            if(currentId.equals(id)){
                updateWrapper.eq("id", currentId).set("isdefault", "true");
                recipientDao.update(null, updateWrapper);
            }else{
                updateWrapper.eq("id", currentId).set("isdefault", "false");
                recipientDao.update(null, updateWrapper);
            }
        }
        return true;
    }

    @Override
    public boolean removeById(String userid, String id){
        QueryWrapper<RecipientEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("id", id);
        RecipientEntity recipientEntity = recipientDao.selectOne(queryWrapper);
        if(recipientEntity != null){
            recipientDao.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateById(String userid, RecipientEntity data){
        QueryWrapper<RecipientEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("id", data.getId());
        RecipientEntity recipientEntity = recipientDao.selectOne(queryWrapper);
        if(recipientEntity != null){
            recipientDao.updateById(data);
            return true;
        }
        return false;
    }
}
