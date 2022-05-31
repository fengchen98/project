package com.shenzhou.logistics.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.logistics.dao.MaterialDao;
import com.shenzhou.logistics.entity.MaterialEntity;
import com.shenzhou.logistics.service.MaterialService;
import org.springframework.stereotype.Service;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 材料商城
 * @Date: 2022/1/28
 * @Time: 0:15
 **/
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialDao, MaterialEntity> implements MaterialService {
}
