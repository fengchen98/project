package com.shenzhou.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.entity.RecipientEntity;

import java.util.List;

/**
 * @ClassName RecipientService
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/10 7:44
 * @Version 1.0
 */
public interface RecipientService extends IService<RecipientEntity> {

    List<RecipientEntity> ListById(String userid);

    boolean setDefaultById(String userid, String id);

    boolean removeById(String userid, String id);

    boolean updateById(String userid, RecipientEntity data);
}
