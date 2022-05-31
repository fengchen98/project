package com.shenzhou.logistics.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 用户
 * @Date: 2022/1/22
 * @Time: 21:24
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("user")
public class UserEntity {
    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 会员等级id
     */
    private Long level_id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 余额
     */
    private float balance;
    /**
     * 返利
     */
    private String rebate;
    /**
     * 优惠券数量
     */
    private String couponcount;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 签名
     */
    private String signature;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 生日
     */
    private Date birth;
    /**
     * 所在城市
     */
    private String city;
    /**
     * 职业
     */
    private String job;
    /**
     * 个性签名
     */
    private String sign;
    /**
     * 用户来源
     */
    private Integer source_type;
    /**
     * 积分
     */
    private Integer integration;
    /**
     * 成长值
     */
    private Integer growth;
    /**
     * 启用状态
     */
    private Integer status;
    /**
     * 注册时间
     */
    private Date create_time;
    /**
     * 社交用户的唯一id
     */
    private String social_uid;
    /**
     * 访问令牌
     */
    private String access_token;
    /**
     * 访问令牌的时间
     */
    private String expires_in;
    /**
     * 城市
     */
    private String country;
    /**
     * 邮编
     */
    private String postcode;
    /**
     * 地区
     */
    private String area;
    /**
     * QQ
     */
    private String qq;
    /**
     * 微信
     */
    private String wechat;
    /**
     * 消费额度
     */
    private String subscribe;

    private float consumption;

    private String idnumber;

    private String confirm_idnumber;

    private String real_name;
}
