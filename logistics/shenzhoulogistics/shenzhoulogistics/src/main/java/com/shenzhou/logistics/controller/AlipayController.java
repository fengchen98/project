package com.shenzhou.logistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shenzhou.logistics.entity.AlipayEntity;
import com.shenzhou.logistics.entity.OrderEntity;
import com.shenzhou.logistics.entity.UserEntity;
import com.shenzhou.logistics.service.OrderService;
import com.shenzhou.logistics.service.PayService;
import com.shenzhou.logistics.service.ProductService;
import com.shenzhou.logistics.service.UserService;
import com.shenzhou.logistics.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 支付接口
 * @Date: 2022/3/22
 * @Time: 0:33
 **/
@CrossOrigin(origins = "*")
@RestController
public class AlipayController {

    @Autowired
    private PayService payService;//调用支付服务

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

//    /*阿里支付*/
//    @PostMapping(value = "/alipay")
//    public String alipay(String out_trade_no, String subject, String total_amount, String body) throws AlipayApiException {
//        return payService.aliPay(new AlipayEntity()
//                .setBody(body)
//                .setOut_trade_no(out_trade_no)
//                .setTotal_amount(new StringBuffer().append(total_amount))
//                .setSubject(subject));
//    }
    @RequestMapping("/alipay/payDemo")
    @ResponseBody
    public byte[] alipayDemo(){
        return payService.alipy();
    }

    @RequestMapping("/alipay/pay/{orderNumber}")
    @ResponseBody
    public byte[] alipay(@PathVariable String orderNumber){
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_number", orderNumber);
        OrderEntity orderEntity = orderService.getOne(queryWrapper, false);
        AlipayEntity alipayEntity = new AlipayEntity(); //  创建支付对象
        alipayEntity.setUser_id(orderEntity.getUser_id());  //  设置用户ID
        alipayEntity.setOut_trade_no(orderEntity.getOrder_number());    // 设置订单号，必填
        alipayEntity.setSubject(orderEntity.getOrder_name());   // 设置订单名称，必填
        alipayEntity.setTotal_amount(String.valueOf(orderEntity.getReal_price()));  //  设置订单金额，必填
        alipayEntity.setPtype(orderEntity.getPtype());
        alipayEntity.setUser_name(orderEntity.getUser_name());
//        System.out.println("alipayEntity构建完成" + alipayEntity);
        return payService.alipy(alipayEntity);  // 调用支付宝支付接口，返回支付二维码
    }

    @ResponseBody
    @RequestMapping("/alipay/notifyUrl")
    public R notifyUrl(HttpServletRequest httpServletRequest) throws Exception{
        boolean isPay = alipayCallback(httpServletRequest);
        if(isPay){
            return R.ok().put("data", "支付成功");
        }
        return R.error(14000, "支付失败");
    }



    public boolean alipayCallback(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        // ------------------处理回调参数--------------------
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = httpServletRequest.getParameterMap();
        System.out.println(requestParams);
        for (Object o : requestParams.keySet()) {
            String name = (String) o;
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for(int i = 0; i < values.length; i++){
                    valueStr = ( i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, new String(valueStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }
        System.out.println("支付宝回调参数：" + params);
        // 支付宝交易号
        String payTime = params.get("gmt_create");
        String tradeno = params.get("trade_no");
        // 参数体
        String body = params.get("body");
        System.out.println("alipayCallback解析的body：" + body);
        ///////////////////////////////////////////////////

        try {
            // -----------------获取支付账单信息-----------------
            JSONObject bodyJson = JSONObject.parseObject(body);
            String userId = bodyJson.getString("userId");
            String username = bodyJson.getString("username");
            String ptype = bodyJson.getString("ptype"); // 支付类型
            String orderNumber = bodyJson.getString("orderNumber");
            String money = bodyJson.getString("money");
            //////////////////////////////////////////////////

            // -----------------获取用户对象--------------------
            QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", userId).eq("username", username);
            UserEntity userEntity = userService.getOne(queryWrapper);
            ////////////////////////////////////////////////////

            // -----------------设置订单状态参数-------------------
            UpdateWrapper<OrderEntity> updateWrapper = new UpdateWrapper<>();

            updateWrapper
                    .eq("order_number", orderNumber)
                    .eq("user_id", userId)
                    .set("status", 1)
                    .set("alipay_deal_number", tradeno)
                    .set("pay_time", payTime);
            ///////////////////////////////////////////////////

            // --------------------判断支付类型，执行对应逻辑------------
            // 如果为alipay,即为支付宝支付,需要更新用户消费额度，更新订单支付后的账户余额
            if(ptype != null && ptype.equalsIgnoreCase("alipay")){
                // 获取用户更新对象,更新用户消费额
                UpdateWrapper<UserEntity> userUpdateWrapper = new UpdateWrapper<>();
                userUpdateWrapper
                        .eq("id", userId)
                        .eq("username", username)
                        .set("consumption", userEntity.getConsumption() + Float.parseFloat(money));
                userService.update(userEntity, userUpdateWrapper);
                // 更新订单支付后余额
                updateWrapper.set("balance", userEntity.getBalance());
            }
            // 如果为balancePay,即为余额支付,扣除余额
//            else if(ptype != null && ptype.equalsIgnoreCase("balancePay")){
//                userEntity.setBalance(userEntity.getBalance() - Float.parseFloat(money));
//                userService.update(userEntity, null);
//            }
            // 如果为充值,需要根棍用户的消费额度和用户账户余额，更新订单充值后的账户余额
            if(ptype != null && ptype.equalsIgnoreCase("recharge")){
                // 获取用户更新对象,更新用户消费额
                UpdateWrapper<UserEntity> userUpdateWrapper = new UpdateWrapper<>();
                userUpdateWrapper
                        .eq("id", userId)
                        .eq("username", username)
                        .set("consumption", userEntity.getConsumption() + Float.parseFloat(money))
                        .set("balance", userEntity.getBalance() + Float.parseFloat(money));
                userService.update(userEntity, userUpdateWrapper);
                // 更新订单充值后余额
                updateWrapper.set("balance", userEntity.getBalance() + Float.parseFloat(money));
            }
            ////////////////////////////////////////////////////////////

            // ----------------------更新订单状态----------------------
            orderService.update(updateWrapper);
            ////////////////////////////////////////////////////////

            // ----------------------更新商品库存和销量----------------
            productService.updateSalesAndStock(orderNumber);
            ///////////////////////////////////////////////////////

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
