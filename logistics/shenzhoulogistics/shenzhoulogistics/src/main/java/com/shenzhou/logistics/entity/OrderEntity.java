package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import javax.xml.soap.Text;

/**
 * @ClassName OrderEntity
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/13 12:22
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_order")
public class OrderEntity {

    @TableId
    private String order_id;    // 订单ID
    private String order_number;    // 订单ID
    private String order_name;  // 订单名称
    private String order_desc;  // 订单描述
    private String user_id; // 用户ID
    private String user_name;   // 用户名
    private String product_list;    // 商品列表
    private String address; // 收货地址
    private String status;  // 订单状态
    private float total_price; // 订单总价
    private float freight_price;   // 运费价格
    private float discount;    // 优惠价格
    private float real_price;  // 支付价格
    private String create_time; // 订单创建时间
    private String pay_time;    // 订单支付时间
    private String ship_time;   // 订单发货时间
    private String finish_time; // 订单完成时间
    private String alipay_deal_number;  // 支付宝交易号
    private String snapshot;    // 订单快照
    private String remark;    // 订单备注
    private String ptype;    // 支付方式
    private String balance;    // 账户余额

}
