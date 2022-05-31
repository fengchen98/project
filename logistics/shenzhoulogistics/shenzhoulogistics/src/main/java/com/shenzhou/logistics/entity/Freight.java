package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor//全参构造器
@NoArgsConstructor//无参构造器
@Data//lombok会帮助自动生成对应方法
@TableName("freight")//指定是数据库里面哪张表
public class Freight {
    @TableId
    private Integer id;
    private double weight_min;
    private double weight_max;
    private double self_delivery;
    private double call_pickup;
    private double self_delivery_depot;
    private double document_express;
    private double priority_package;
    private double economic_package;
    private double small_package;
    private double belgium_post;
    private double six_milk_powder;
    private double four_milk_powder;
    private double grocery_tax;
    private double france_post;
    private double grocery_package;
    private double skin_care_package;
    private double suit_special_line;
    private double plus_groceries;
    private double sea_transportation;
    private double air_transportation;
    private double two_milk_powder;
}
//@TableName("test")//指定是数据库里面哪张表
//public class Freight {
//    @TableId
//    private Integer id;
//    private double mixname;
//    private double minname;
//}
