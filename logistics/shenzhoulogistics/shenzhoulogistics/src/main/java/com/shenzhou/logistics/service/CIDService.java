package com.shenzhou.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.entity.CIDEntity;

/**
 * @ClassName CIDService
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/5/14 14:08
 * @Version 1.0
 */
public interface CIDService extends IService<CIDEntity> {

    CIDEntity getByUserID(String user_id);
}
