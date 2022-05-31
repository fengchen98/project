package com.shenzhou.logistics.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.logistics.dao.FreightDao;
import com.shenzhou.logistics.entity.FreightEntity;
import com.shenzhou.logistics.entity.ProductEntity;
import com.shenzhou.logistics.entity.SkuInfoEntity;
import com.shenzhou.logistics.service.FreightService;
import com.shenzhou.logistics.utils.PageUtils;
import com.shenzhou.logistics.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 运费报价
 * @Date: 2022/1/25
 * @Time: 21:32
 **/
@Service
public class FreightServiceImpl extends ServiceImpl<FreightDao, FreightEntity> implements FreightService {

    @Autowired
    private FreightDao freightDao;



    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<FreightEntity> queryWrapper = new QueryWrapper<>();
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

//        String catelogId = (String) params.get("weightMin");
//        if (!StringUtils.isEmpty(catelogId) && !"0".equalsIgnoreCase(catelogId)) {
//
//            queryWrapper.eq("weightMin", catelogId);
//        }
//
//        String brandId = (String) params.get("weightMax");
//        if (!StringUtils.isEmpty(brandId) && !"0".equalsIgnoreCase(catelogId)) {
//            queryWrapper.eq("weightMax", brandId);
//        }

        String min = (String) params.get("weightMin");
        if (!StringUtils.isEmpty(min)) {
            queryWrapper.ge("weightMin", min);
        }

        String max = (String) params.get("weightMax");

        if (!StringUtils.isEmpty(max)) {
            try {
                BigDecimal bigDecimal = new BigDecimal(max);

                if (bigDecimal.compareTo(new BigDecimal("0")) == 1) {
                    queryWrapper.le("weightMax", max);
                }
            } catch (Exception e) {

            }
        }
        IPage<FreightEntity> page = this.page(
                new Query<FreightEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }


//    @Override
//    public void selectFreightInfo() {
//
//    }

    //    @Override
//    public void selectFreightInfo() {
//        this.baseMapper.selectList(freightEntity);
//    }
//    public List<FreightEntity> ListFreight(){
//        return freightDao.ListFreight();
//    }
}
