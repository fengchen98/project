package com.shenzhou.logistics.controller;

import com.auth0.jwt.JWT;
import com.shenzhou.logistics.dao.ProductDao;
import com.shenzhou.logistics.entity.FreightEntity;
import com.shenzhou.logistics.entity.ProductEntity;
import com.shenzhou.logistics.entity.SkuInfoEntity;
import com.shenzhou.logistics.service.ProductService;
import com.shenzhou.logistics.service.UserService;
import com.shenzhou.logistics.utils.PageUtils;
import com.shenzhou.logistics.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 奶粉商城
 * @Date: 2022/1/28
 * @Time: 13:09
 **/
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 所哟有商品列表
     */
    @RequestMapping("/list")
    public R listProduct(HttpServletRequest httpServletRequest){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
            return R.ok().put("data", productService.selectList());
        }else{
            return R.error(14000, "用户登录凭证已过期");
        }
    }

    @RequestMapping("/onSale")
    public R onSale(HttpServletRequest httpServletRequest, @RequestBody long[] ids){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
            return R.ok().put("data", productService.onSale(ids));
        }else{
            return R.error(14000, "用户登录凭证已过期");
        }
    }

    @RequestMapping("/offSale")
    public R offSale(HttpServletRequest httpServletRequest, @RequestBody long[] ids){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
            return R.ok().put("data", productService.offSale(ids));
        }else{
            return R.error(14000, "用户登录凭证已过期");
        }
    }

    @RequestMapping("/onSaleList")
    public R onSaleList(HttpServletRequest httpServletRequest){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
            return R.ok().put("data", productService.onSaleList());
        }else{
            return R.error(14000, "用户登录凭证已过期");
        }
    }

    @RequestMapping("/offSaleList")
    public R offSaleList(HttpServletRequest httpServletRequest){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
            return R.ok().put("data", productService.offSaleList());
        }else{
            return R.error(14000, "用户登录凭证已过期");
        }
    }
    /**
     * 返回上架商品
     */
//    @RequestMapping("/list")
//    public R listShelvesProduct(HttpServletRequest httpServletRequest){
//        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
//        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
//            return R.ok().put("data", productService.selectShelvesList());
//        }else{
//            return R.error(14000, "用户登录凭证已过期");
//        }
//    }

    /**
     * 修改上下架信息
     */
    @RequestMapping("/isShelves/{type}")
    public R changeProductShelves(HttpServletRequest httpServletRequest, @PathVariable String type, @RequestBody long[] ids){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
            if(Objects.equals(type,"onSale")){
                return R.ok().put("data", productService.onSale(ids));
            }
            if(Objects.equals(type,"offSale")){
                return R.ok().put("data", productService.offSale(ids));
            }
            return R.error();
        }else{
            return R.error(14000, "用户登录凭证已过期");
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delList")
    public R listAllProduct(HttpServletRequest httpServletRequest){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
            return R.ok().put("data", productService.selectIsDelete());
        }else{
            return R.error(14000, "用户登录凭证已过期");
        }
    }

    /**
     * 条件查询
     */
    @RequestMapping("/search")
    public R searchProducts(@RequestParam Map<String, Object> params) {
        PageUtils page = productService.queryPageByCondition(params);
        return R.ok().put("page", page);
    }

    /**
     * 商品详情信息
     */
    @RequestMapping("/getinfo/{id}")
    public R info(@PathVariable("id") long id) {
        return R.ok().put("data", productService.getById(id));
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(HttpServletRequest httpServletRequest, @RequestBody ProductEntity productEntity) {
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
            if(productService.save(productEntity)){
                return R.ok().put("data", productEntity);
            }
            return R.error(14000, "保存失败");
        }else{
            return R.error(14000, "用户登录凭证已过期");
        }
    }

//    /**
//     * 保存+图片
//     */
//    @RequestMapping("/save")
//    public R save(
//            HttpServletRequest httpServletRequest,                              // 请求体
//            @RequestParam("info") ProductEntity productEntity,                  // 商品基本信息
//            @RequestParam("images") MultipartFile[] images,                     // 商品图片
//            @RequestParam("introduce_images") MultipartFile[] introduce_images  // 商品介绍图片
//    ) {
//        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
//        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
//            if(productService.save(productEntity)){
//                System.out.println("刚才添加的对象id： " + productEntity.getId());
//                return R.ok();
//            }
//            return R.error(14000, "保存失败");
//        }else{
//            return R.error(14000, "用户登录凭证已过期");
//        }
//    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(HttpServletRequest httpServletRequest, @RequestBody ProductEntity productEntity) {
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
            if(productService.updateById(productEntity)){
                return R.ok();
            }
            return R.error(14000, "更新失败");
        }else{
            return R.error(14000, "用户登录凭证已过期");
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(HttpServletRequest httpServletRequest, @RequestBody Long[] ids) {
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
//            if(productService.removeByIds(Arrays.asList(id))){
            if(productService.changeDeleteMarkByIds(ids)){
                return R.ok();
            }
            return R.error(14000, "更新失败");
        }else{
            return R.error(14000, "用户登录凭证已过期");
        }
    }
}
