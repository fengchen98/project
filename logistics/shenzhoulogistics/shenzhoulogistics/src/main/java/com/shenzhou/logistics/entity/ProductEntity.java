package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 奶粉商城
 * @Date: 2022/1/28
 * @Time: 0:07
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("product")
public class ProductEntity {

    @TableId
    private Long id;    // 唯一id
    private String serial_id;   // 商品编号（与id一样）
    private String brand;   // 品牌
    private String catalog; // 分类
    private long brand_id;  // 品牌ID
    private Long catalog_id;    // 分类ID
    private String title;   // 商品标题
    private String title_color; // 标题颜色
    private String simple_desc; // 商品简介
    private String model;   // 商品模式
    private long place_origin;  // 生产地
    private long inventory; // 存货
    private String inventory_unit;  // 存货单位
    private String images;  // 图片列表
    private String introduce_images;    // 介绍图片列表
    private String original_price;  // 原始价格
    private double min_original_price;  // 最低原始价格
    private double max_original_price;  // 最高原始价格
    private float price;    // 现价
    private double min_price;   // 最低价格
    private double max_price;   // 最高价格
    private long give_integral; //
    private long buy_min_number;    // 购买最低数量
    private long buy_max_number;    // 购买最高数量
    private long is_deduction_inventory;    // 扣减库存
    private long is_shelves;    // 货架
    private long is_home_recommended;   // 推荐
    private String content_web; // 页面内容
    private long photo_count;   // 图片数量
    private long sales_count;   // 销售数量
    private long access_count;  // 许可数量
    private String home_recommended_images; // 推荐图片
    private long is_exist_many_spec;    // 存在其他规格
    private String seo_title;   // 搜索引擎优化标题
    private String seo_keywords;    // 搜索引擎优化关键字
    private String seo_desc;    // 搜索引擎优化描述
    private int is_delete;    // 删除表示
    private String is_delete_time;    // 删除时间
    private long upd_time;  // 更新时间
    private int stock;   // 库存
    private double length;  // 商品长度
    private double width;   // 商品宽度
    private double height;  // 商品高度
    private double weight;  // 商品重量
    private String milk_rank;   // 奶粉段位
    private String blue_cap_mark;   // 蓝帽标识
    private String net_weight;  // 净含量
    private String dosage_form; // 剂型
    private String origin_place;    // 原产地
    private String suit_age;    // 适合年龄
    private String spec_type;   // 规格类型
    private String package_type;    // 包装类型
    private String ingredient;  //  原材料
    private String more;    // 评论量
    private String description;    // 描述


}
