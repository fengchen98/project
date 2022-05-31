package com.shenzhou.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.entity.ProductEntity;
import com.shenzhou.logistics.entity.SkuInfoEntity;
import com.shenzhou.logistics.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 奶粉商城
 * @Date: 2022/1/28
 * @Time: 0:13
 **/
public interface ProductService extends IService<ProductEntity> {

    PageUtils queryPageByCondition(Map<String, Object> params);

    boolean changeDeleteMarkByIds(Long[] ids);

    List<ProductEntity> selectList();

    List<ProductEntity> onSaleList();

    List<ProductEntity> offSaleList();

    List<ProductEntity> selectShelvesList();

    List<ProductEntity> selectIsDelete();

    boolean onSale(long[] ids);

    boolean offSale(long[] ids);

    void updateSalesAndStock(String orderNumber);

}
