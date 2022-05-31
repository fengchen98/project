package com.shenzhou.logistics.controller;

import com.shenzhou.logistics.dao.MaterialDao;
import com.shenzhou.logistics.entity.MaterialEntity;
import com.shenzhou.logistics.service.MaterialService;
import com.shenzhou.logistics.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 材料商城
 * @Date: 2022/1/22
 * @Time: 10:27
 **/
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialDao materialDao;


    /**
     * 包装材料列表
     * @return
     */
    @RequestMapping("/list")
    public List<MaterialEntity> listMaterial(){
        return  materialDao.selectList(null);
    }


    /**
     * 材料详情信息
     */
    @RequestMapping("/getinfo/{id}")
    public R info(@PathVariable("id") long id) {
        MaterialEntity materialEntity = materialService.getById(id);

        return R.ok().put("MaterialEntity", materialEntity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MaterialEntity materialEntity) {
        materialService.save(materialEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MaterialEntity materialEntity) {
        materialService.updateById(materialEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] id) {
        materialService.removeByIds(Arrays.asList(id));

        return R.ok();
    }
}
