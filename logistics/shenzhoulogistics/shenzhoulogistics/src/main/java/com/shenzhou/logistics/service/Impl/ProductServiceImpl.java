package com.shenzhou.logistics.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.logistics.dao.OrderDetailDao;
import com.shenzhou.logistics.dao.ProductDao;
import com.shenzhou.logistics.entity.OrderDetailEntity;
import com.shenzhou.logistics.entity.ProductEntity;
import com.shenzhou.logistics.service.ProductService;
import com.shenzhou.logistics.utils.PageUtils;
import com.shenzhou.logistics.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 奶粉商城
 * @Date: 2022/1/28
 * @Time: 0:15
 **/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, ProductEntity> implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        /**
         * key:
         * catelogId: 0
         * brandId: 0
         * min: 0
         * max: 0
         */
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and((wrapper) -> {
                wrapper.eq("id", key).or().like("title", key);
            });
        }

        String catelogId = (String) params.get("catelogId");
        if (!StringUtils.isEmpty(catelogId) && !"0".equalsIgnoreCase(catelogId)) {

            queryWrapper.eq("catalog_id", catelogId);
        }

        String brandId = (String) params.get("brandId");
        if (!StringUtils.isEmpty(brandId) && !"0".equalsIgnoreCase(catelogId)) {
            queryWrapper.eq("brand_id", brandId);
        }

        String min = (String) params.get("minOriginalPrice");
        if (!StringUtils.isEmpty(min)) {
            queryWrapper.ge("price", min);
        }

        String max = (String) params.get("maxOriginalPrice");

        if (!StringUtils.isEmpty(max)) {
            try {
                BigDecimal bigDecimal = new BigDecimal(max);

                if (bigDecimal.compareTo(new BigDecimal("0")) == 1) {
                    queryWrapper.le("price", max);
                }
            } catch (Exception e) {

            }
        }
        IPage<ProductEntity> page = this.page(
                new Query<ProductEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public boolean changeDeleteMarkByIds(Long[] ids) {
        for(Long id:ids){
            try {
                ProductEntity productEntity = productDao.selectById(id);
                productEntity.setIs_delete(1);
                productEntity.setIs_delete_time(new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(new Date()));
                productDao.updateById(productEntity);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public List<ProductEntity> selectList() {
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0);
        return productDao.selectList(queryWrapper);
    }

    @Override
    public List<ProductEntity> onSaleList() {
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0).eq("is_shelves", 1);
        return productDao.selectList(queryWrapper);
    }

    @Override
    public List<ProductEntity> offSaleList() {
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0).eq("is_shelves", 0);
        return productDao.selectList(queryWrapper);
    }

    @Override
    public List<ProductEntity> selectShelvesList() {
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0).eq("is_shelves", 1);
        return productDao.selectList(queryWrapper);
    }

    @Override
    public List<ProductEntity> selectIsDelete() {
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 1);
        return productDao.selectList(queryWrapper);
    }

    @Override
    public boolean onSale(long[] ids) {
        for(Long id:ids){
            try {
                ProductEntity productEntity = productDao.selectById(id);
                productEntity.setIs_shelves(1);
                productDao.updateById(productEntity);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean offSale(long[] ids) {
        for(Long id:ids){
            try {
                ProductEntity productEntity = productDao.selectById(id);
                productEntity.setIs_shelves(0);
                productDao.updateById(productEntity);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    @Override
    public void updateSalesAndStock(String orderNumber) {
        QueryWrapper<OrderDetailEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_number", orderNumber);
        List<OrderDetailEntity> orderDetailEntities = orderDetailDao.selectList(queryWrapper);
        for(OrderDetailEntity  orderDetailEntity:orderDetailEntities){
            ProductEntity productEntity = productDao.selectById(orderDetailEntity.getSerial_id());
            UpdateWrapper<ProductEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper
                    .eq("id", orderDetailEntity.getSerial_id())
                    .set("sales_count", productEntity.getSales_count() + orderDetailEntity.getAmount())
                    .set("stock", productEntity.getStock() - orderDetailEntity.getAmount());
            productDao.update(productEntity, updateWrapper);
        }
    }

}
