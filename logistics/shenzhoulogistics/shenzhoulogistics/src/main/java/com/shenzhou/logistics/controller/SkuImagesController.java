package com.shenzhou.logistics.controller;

import java.util.Arrays;
import java.util.Map;

import com.shenzhou.logistics.entity.SkuImagesEntity;
import com.shenzhou.logistics.service.SkuImagesService;
import com.shenzhou.logistics.utils.PageUtils;
import com.shenzhou.logistics.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * sku图片
 *
 * @author jinlei
 * @date 2022-01-25 22:50:32
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/skuimages")
public class SkuImagesController {

    @Autowired
    private SkuImagesService skuImagesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuImagesService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SkuImagesEntity skuImages = skuImagesService.getById(id);

        return R.ok().put("data", skuImages);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SkuImagesEntity skuImages){
		skuImagesService.save(skuImages);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SkuImagesEntity skuImages){
		skuImagesService.updateById(skuImages);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		skuImagesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
