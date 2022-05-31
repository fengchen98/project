package com.shenzhou.logistics.service.Impl;

import com.shenzhou.logistics.dao.SkuImagesDao;
import com.shenzhou.logistics.entity.SkuImagesEntity;
import com.shenzhou.logistics.service.SkuImagesService;
import com.shenzhou.logistics.utils.PageUtils;
import com.shenzhou.logistics.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * @author jinlei
 * @date 2022-01-23 21:08:49
 */
@Service
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesDao, SkuImagesEntity> implements SkuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuImagesEntity> page = this.page(
                new Query<SkuImagesEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuImagesEntity> getImagesBySkuId(Long skuId) {
        return this.baseMapper.selectList(new QueryWrapper<SkuImagesEntity>().eq("sku_id", skuId));
    }
}
