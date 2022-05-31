package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName cartEntity
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/9 13:41
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("cart")
public class CartEntity {

    @TableId
    private String id;

    private String serial_id;

    private String user_id;

    private String name;

    private String description;

    private float unitprice;

    private int amount;

    private float totalprice;

    private String images;

    private double length;

    private double width;

    private double height;

    private double weight;

}
