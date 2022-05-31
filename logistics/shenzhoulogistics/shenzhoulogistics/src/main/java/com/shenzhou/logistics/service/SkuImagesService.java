package com.shenzhou.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.entity.SkuImagesEntity;
import com.shenzhou.logistics.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * sku图片
 * @author jinlei
 * @date 2022-01-24 21:08:49
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuImagesEntity> getImagesBySkuId(Long skuId);
}

