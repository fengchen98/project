package com.shenzhou.logistics.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.Car;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shenzhou.logistics.Exception.BizCodeEnum;
import com.shenzhou.logistics.entity.*;
import com.shenzhou.logistics.service.*;
import com.shenzhou.logistics.utils.R;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/13 12:28
 * @Version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/order")
public class OrderController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDetailService orderDetailService;


    // 创建余额支付订单
    @RequestMapping("/pay_by_balance")
    public R createBalancePayOrder(){
        return R.ok();
    }

    // 创建充值订单
    @RequestMapping("/create_Recharge_Order")
    public R createRechargeOrder(HttpServletRequest httpServletRequest, @RequestParam("money") String money){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        UserEntity user = userService.getById(userId);
        OrderEntity orderEntity = new OrderEntity();

        String OrderNumber = UUID.randomUUID().toString().replaceAll("-", "");

        orderEntity.setOrder_number(OrderNumber);
        orderEntity.setOrder_name(user.getUsername() + "充值余额订单");
        orderEntity.setOrder_desc(user.getUsername() + "充值余额订单");
        orderEntity.setUser_id(userId);
        orderEntity.setUser_name(user.getUsername());
        orderEntity.setStatus("0");
        orderEntity.setTotal_price(Float.parseFloat(money));
        orderEntity.setDiscount(0); // 优惠
        orderEntity.setReal_price(orderEntity.getTotal_price() - orderEntity.getDiscount());    // 支付金额 = 商品总价 + 运费 - 优惠
        orderEntity.setCreate_time(new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(new Date()));    // 订单创建时间
        orderEntity.setPtype("recharge");    // 订单支付类型
        orderService.save(orderEntity);

        QueryWrapper<OrderEntity> orderEntityQueryWrapper = new QueryWrapper<>();
        orderEntityQueryWrapper.eq("order_number", OrderNumber);
        try {
            if(orderService.getOne(orderEntityQueryWrapper) != null){
                redisTemplate.opsForValue().set(OrderNumber, 1, Duration.ofMinutes(30));
                return R.ok().put("data", OrderNumber);
            }
            return R.error(14000, "订单创建失败");
        } catch (Exception e){
            return R.error(14000, e.toString());
        }
    }

    // 创建支付宝支付订单
    @RequestMapping("/create_order")
    public R createOrder(HttpServletRequest httpServletRequest, @RequestParam("goodsids") List<String> goodsids, @RequestParam("address") String address){
        // 获取用户信息
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        UserEntity user = userService.getById(userId);
        // 获取收件信息
        RecipientEntity recipientEntity = JSON.toJavaObject(JSONObject.parseObject(address), RecipientEntity.class);
//        System.out.println(recipientEntity);
        // 生成订单号
        String OrderNumber = UUID.randomUUID().toString().replaceAll("-", "");
        // 计算商品总价
        float total_prices = 0;
        for(String id:goodsids ){
            CartEntity cartEntity = cartService.getById(id); // 获取购物车对象
            String prod_id = cartEntity.getSerial_id(); // 从购物车对象获取商品ID
            int prod_amount = cartEntity.getAmount();   // 从购物车对象获取商品数量
            ProductEntity productEntity = productService.getById(prod_id);  // 获取商品对象
            float unit_price = productEntity.getPrice();    // 获取商品单价
            float total_price = unit_price * prod_amount; // 计算总价
            total_prices += total_price;    // 计算总价

            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();  // 创建子订单对像
            BeanUtils.copyProperties(cartEntity, orderDetailEntity);    // 迁移属性
            orderDetailEntity.setOrder_number(OrderNumber); // 设置订单号
            orderDetailEntity.setUnitprice(unit_price); // 设置商品单价
            orderDetailEntity.setAmount(prod_amount);   // 设置商品数量
            orderDetailEntity.setTotalprice(total_price);   // 设置商品总价

            orderDetailService.save(orderDetailEntity);
            cartService.removeById(id);
//            total_price += productService.getById(orderDetailEntity.getSerial_id()).getPrice() * orderDetailEntity.getAmount();
//            // 创建子订单
//            orderDetailEntity.setUserid(userId);
//            orderDetailEntity.setOrder_number(OrderNumber);
//            orderDetailService.save(orderDetailEntity);
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrder_number(OrderNumber);
        orderEntity.setOrder_name(user.getUsername() + "在线支付商品订单");
        orderEntity.setOrder_desc(user.getUsername() + "的订单" + OrderNumber + ",创建于" + new Date().getTime());
        orderEntity.setUser_id(userId);
        orderEntity.setUser_name(user.getUsername());
        orderEntity.setAddress(recipientEntity.getName() + " " + recipientEntity.getPhone() + " " + recipientEntity.getArea() + recipientEntity.getAddress());
        orderEntity.setStatus("0");
        orderEntity.setFreight_price(0); // 运费
        orderEntity.setDiscount(0); // 优惠
        orderEntity.setTotal_price(total_prices);
        orderEntity.setReal_price(total_prices + orderEntity.getFreight_price() - orderEntity.getDiscount());    // 支付金额 = 商品总价 + 运费 - 优惠
        orderEntity.setCreate_time(new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(new Date()));    // 订单创建时间
        orderEntity.setRemark(recipientEntity.getRemark()); //订单备注
        orderEntity.setPtype("alipay");    // 订单支付类型
//        orderEntity.setSnapshot(new StringBuffer());
        orderService.save(orderEntity);

        QueryWrapper<OrderEntity> orderEntityQueryWrapper = new QueryWrapper<>();
        orderEntityQueryWrapper.eq("order_number", OrderNumber);
        try {
            if(orderService.getOne(orderEntityQueryWrapper) != null){
                redisTemplate.opsForValue().set(OrderNumber, 1, Duration.ofMinutes(30));
//                AlipayController alipayController = new AlipayController();
//                AlipayEntity alipayEntity = new AlipayEntity();
//                alipayEntity.setOut_trade_no(OrderNumber);
//                alipayEntity.setSubject(orderEntity.getOrder_name());
//                alipayEntity.setTotal_amount(new StringBuffer(String.valueOf(orderEntity.getReal_price())));
//                alipayEntity.setBody(orderEntity.getOrder_desc());
//                alipayController.alipay(OrderNumber, orderEntity.getOrder_name(), String.valueOf(orderEntity.getReal_price()), orderEntity.getOrder_desc())
                return R.ok().put("data", OrderNumber);
            }
            return R.error(14000, "订单创建失败");
        } catch (Exception e){
            return R.error(14000, e.toString());
        }
    }

    // 查询订单详情
    @RequestMapping("/queryOrder")
    public R queryOrder(HttpServletRequest httpServletRequest, @RequestParam("orderNumber") String orderNumber){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
            QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("order_number", orderNumber);
            return R.ok().put("data", orderService.getOne(queryWrapper, false));
        }
        return R.error(14000, "用户登录状态过期，请重新登录");
    }

    @RequestMapping("/queryOrderDetail")
    public R queryOrderDetail(HttpServletRequest httpServletRequest,@RequestParam("orderNumber") String orderNumber){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
            return R.ok().put("data", orderDetailService.getListByOrderNumber(orderNumber));
        }
        return R.error(14000, "用户登录状态过期，请重新登录");
    }

    @RequestMapping("/queryUserOrders")
    public R queryUserOrders(HttpServletRequest httpServletRequest){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
            return R.ok().put("data", orderService.queryUserOrders(userId));
        }
        return R.error(14000, "用户登录状态过期，请重新登录");
    }

    @RequestMapping("/cancelOrder")
    public R cancelOrder(HttpServletRequest httpServletRequest, @RequestParam("orderNumber") String orderNumber){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        if(Objects.equals(redisTemplate.hasKey(userId), Boolean.TRUE)){
            UpdateWrapper<OrderEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id", userId).eq("order_number", orderNumber).set("status", 2).set("finish_time", new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(new Date()));
            return R.ok().put("data", orderService.update(updateWrapper));
        }
        return R.error(14000, "用户登录状态过期，请重新登录");
    }

    @RequestMapping("/testOrder")
    public R testOrderCreate(HttpServletRequest httpServletRequest, @RequestParam("ids") List<String> ids, @RequestParam("address") String address){
//        RecipientEntity recipientEntity = (RecipientEntity) JSON.parse(address);
//          System.out.println(ids);
//        RecipientEntity recipientEntity = JSON.toJavaObject(JSONObject.parseObject(address), RecipientEntity.class);
//        System.out.println(recipientEntity);
//        List<CartEntity> cartEntities = JSON.parseArray(list, CartEntity.class);
//        System.out.println(cartEntities);
//        OrderEntity orderEntity = JSON.toJavaObject(JSONObject.parseObject(payinfo), OrderEntity.class);
//        System.out.println(orderEntity);
        return R.ok();
    }

    @RequestMapping("/allOrder")
    public R allOrder(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(userService.getById(userId).getLevel_id() >= 5){
            return R.ok().put("data", orderService.list());
        }else {
            return R.error(14000, "用户权限不足");
        }
    }

}
