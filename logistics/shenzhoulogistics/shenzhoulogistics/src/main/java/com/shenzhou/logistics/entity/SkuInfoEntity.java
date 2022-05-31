package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sku信息
 * @author jinlei
 * @date 2022-01-24 21:08:49
 */
@Data
@TableName("sku_info")
public class SkuInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * skuId
	 */
	@TableId
	private Long skuid;
	/**
	 * spuId
	 */
	private Long spuid;
	/**
	 * sku中文名称
	 */
	private String skucname;
	/**
	 * sku英文名称
	 */
	private String skuename;
	/**
	 * sku介绍描述
	 */
	private String skudesc;
	/**
	 * 所属分类
	 */
	private String catalog;
	/**
	 * 所属分类id
	 */
	private Long catalogid;
	/**
	 * 品牌
	 */
	private String brand;
	/**
	 * 品牌id
	 */
	private Long brandid;
	/**
	 * 默认图片
	 */
	private String skudefaultimg;
	/**
	 * 标题
	 */
	private String skutitle;
	/**
	 * 副标题
	 */
	private String skusubtitle;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 销量
	 */
	private Long salecount;

}
