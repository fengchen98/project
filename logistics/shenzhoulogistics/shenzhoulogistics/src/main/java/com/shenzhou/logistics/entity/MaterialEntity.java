package com.shenzhou.logistics.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 材料商城
 * @Date: 2022/1/25
 * @Time: 21:31
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("material")
public class MaterialEntity {

  private Long id;
  /**
   * 包装材料分类
   */
  private String category;
  /**
   * 包装材料名称
   */
  private String title;
  /**
   * 价格
   */
  private double price;
  /**
   * 包装材料描述
   */
  private String describe;
  /**
   * 包装材料详情图
   */
  private String image;
  /**
   * 包装材料重量
   */
  private int weight;
  /**
   * 长
   */
  private int length;
  /**
   * 宽
   */
  private int width;
  /**
   * 高
   */
  private int height;
  /**
   * 数量
   */
  private int number;


}
