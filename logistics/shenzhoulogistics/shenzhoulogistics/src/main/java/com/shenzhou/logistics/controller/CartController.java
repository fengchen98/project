package com.shenzhou.logistics.controller;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shenzhou.logistics.entity.CartEntity;
import com.shenzhou.logistics.entity.ProductEntity;
import com.shenzhou.logistics.service.CartService;
import com.shenzhou.logistics.service.ProductService;
import com.shenzhou.logistics.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName CartController
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/9 13:49
 * @Version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;


    @RequestMapping("/list")
    public R ListById(HttpServletRequest httpServletRequest){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        List<CartEntity> cartEntities =  cartService.ListById(userId);
        for(CartEntity cartEntity:cartEntities){
            cartEntity.setUnitprice(productService.getById(cartEntity.getSerial_id()).getPrice());
            cartEntity.setTotalprice(productService.getById(cartEntity.getSerial_id()).getPrice() * cartEntity.getAmount());
        }
        return R.ok().put("data", cartEntities);
    }

    @RequestMapping("/listNumber")
    public R listNumber(HttpServletRequest httpServletRequest){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        List<CartEntity> cartEntities =  cartService.ListById(userId);
        int listNumber = 0;
        for(CartEntity cartEntity:cartEntities){
            listNumber += cartEntity.getAmount();
        }
        return R.ok().put("data", listNumber);
    }

    @RequestMapping("/save/{id}/{number}")
    public R save(HttpServletRequest httpServletRequest, @PathVariable String id, @PathVariable int number){
        String userId =  JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        QueryWrapper<CartEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("serial_id", id);
        CartEntity cartEntity = cartService.getOne(queryWrapper, false);
        // 检查商品是否已添加到购物车
        if(cartEntity != null){
            cartEntity.setAmount(cartEntity.getAmount() + number);
            if(cartService.updateById(cartEntity)){
                return R.ok();
            }else {
                return R.error(14000, "添加购物车失败");
            }
        } else {
            CartEntity cartEntity1 = new CartEntity();
            ProductEntity productEntity = productService.getById(id);
            cartEntity1.setSerial_id(productEntity.getId().toString());
            cartEntity1.setUser_id(userId);
            cartEntity1.setName(productEntity.getTitle());
            cartEntity1.setUnitprice(productEntity.getPrice());
            cartEntity1.setAmount(number);
            cartEntity1.setTotalprice(productEntity.getPrice() * number);
            cartEntity1.setImages(productEntity.getImages());
            cartEntity1.setLength(productEntity.getLength());
            cartEntity1.setWidth(productEntity.getWidth());
            cartEntity1.setHeight(productEntity.getHeight());
            cartEntity1.setWeight(productEntity.getWeight());
            if(cartService.save(cartEntity1)){
                return R.ok().put("data", "已添加购物车");
            }
            return R.error(14000, "添加购物车失败");
        }
    }

    @RequestMapping("/remove/{serialId}")
    public R remove(HttpServletRequest httpServletRequest, @PathVariable String serialId){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        QueryWrapper<CartEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("serial_id", serialId);
        if(cartService.remove(queryWrapper)){
            return R.ok("已从购物车删除");
        }
        return R.error(14000, "删除失败，请重试");
    }

    @RequestMapping("/setNumber/{number}")
    public R setNumber(HttpServletRequest httpServletRequest, @RequestBody CartEntity cartEntity, @PathVariable int number){
        if(number < 1){
            return R.error(14000, "商品数量不能少于1件");
        }
        if(number > 99){
            return R.error(14000, "商品数量不能超过99件");
        }
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        String serialId = cartEntity.getSerial_id();
        // 查询商品价格
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", serialId);
        float price = productService.getOne(queryWrapper).getPrice();
        // 查询购物车记录
        QueryWrapper<CartEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id", userId).eq("serial_id", serialId);
        CartEntity cartEntity1 = cartService.getOne(queryWrapper1);
        // 修改购物车记录
        cartEntity1.setAmount(number);  // 更新数量
        cartEntity1.setUnitprice(price);    // 更新单价
        cartEntity1.setTotalprice(price * number);  // 更新总价
        if(cartService.updateById(cartEntity1)){
            return R.ok();
        }else {
            return R.error(14000, "商品数量设置出错");
        }
    }
}
