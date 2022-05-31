package com.shenzhou.logistics.controller;

import com.shenzhou.logistics.dao.FreightDao;
import com.shenzhou.logistics.entity.FreightEntity;
import com.shenzhou.logistics.entity.ProductEntity;
import com.shenzhou.logistics.service.FreightService;
import com.shenzhou.logistics.utils.PageUtils;
import com.shenzhou.logistics.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 运费报价
 * @Date: 2022/1/25
 * @Time: 21:29
 **/
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/freight")
public class FreightController {

    @Autowired
    private FreightService freightService;

    @Autowired
    private FreightDao freightDao;

    @RequestMapping("/list")
    @ResponseBody
    public List<FreightEntity> ListFreight(){

//      return freightService.ListFreight();
        return  freightDao.selectList(null);
    }
    /**
     * 运费查询
     */
    @RequestMapping("/search")
    public R searchProducts(@RequestParam Map<String, Object> params) {
        PageUtils page = freightService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public R save(@RequestBody FreightEntity freightEntity) {
        freightService.save(freightEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody FreightEntity freightEntity) {
        freightService.updateById(freightEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] id) {
        freightService.removeByIds(Arrays.asList(id));

        return R.ok();
    }

}
