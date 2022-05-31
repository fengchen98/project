package com.shenzhou.logistics.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.entity.SkuInfoEntity;
import com.shenzhou.logistics.utils.PageUtils;
import com.shenzhou.logistics.vo.SkuItemVo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * sku信息
 * @author jinlei
 * @date 2022-01-24 21:08:49
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuInfo(SkuInfoEntity skuInfoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);

    /**
     * 查出当前spuId对应的所有sku信息,品牌的名字
     *
     * @param spuId
     * @return
     */
    List<SkuInfoEntity> getSkusBySpuId(Long spuId);

    SkuItemVo item(Long skuId) throws ExecutionException, InterruptedException;
}

