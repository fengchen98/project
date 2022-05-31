package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 运费报价
 * @Date: 2022/1/25
 * @Time: 21:31
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("freight")
public class FreightEntity {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     *重量最小
     */
    private double weightMin;
    /**
     * 重量最大
     */
    private double weightMax;
    /**
     * ParcelForce自送邮局
     */
    private double selfDelivery;
    /**
     * ParcelForce上门取件
     */
    private double callPickup;
    /**
     * ParcelForce自送Depot
     */
    private double selfDeliveryDepot;
    /**
     * UPS文件特快
     */
    private double documentExpress;
    /**
     * ParcelForce优先包
     */
    private double priorityPackage;
    /**
     * ParcelForce经济包
     */
    private double economicPackage;
    /**
     * ParcelForce小包
     */
    private double smallPackage;
    /**
     * Bpost比利时邮政
     */
    private double belgiumPost;
    /**
     * 六罐奶粉
     */
    private double sixMilkPowder;
    /**
     * 四罐奶粉
     */
    private double fourMilkPowder;
    /**
     * 杂货包税
     */
    private double groceryTax;
    /**
     * 法国邮政
     */
    private double francePost;
    /**
     * 杂货小包
     */
    private double  groceryPackage;
    /**
     * 护肤品小包
     */
    private double  skinCarePackage;
    /**
     * 套装专线
     */
    private double  suitSpecialLine;
    /**
     * Plus杂货
     */
    private double  plusGroceries;
    /**
     * 海运行李
     */
    private double  seaTransportation;
    /**
     * 空运行李
     */
    private double  airTransportation;
    /**
     * 两罐奶粉
     */
    private double  twoMilkPowder;
}
