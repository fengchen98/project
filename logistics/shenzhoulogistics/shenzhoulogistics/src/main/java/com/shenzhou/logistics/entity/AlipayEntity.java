package com.shenzhou.logistics.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 支付实体
 * @Date: 2022/3/22
 * @Time: 0:22
 **/
@Data
@Accessors(chain = true)
public class AlipayEntity {

    private String user_id;

    private String user_name;
    /*商户订单号，必填*/
    private String out_trade_no;
    /*订单名称，必填*/
    private String subject;
    /*付款金额，必填*/
    private String total_amount;
    /*商品描述，可空*/
    private String body;
    /*支付方式，可空*/
    private String ptype;
    /*超时时间参数*/
    private String timeout_express = "15m";

    private String product_code = "FAST_INSTANT_TRADE_PAY";
}
