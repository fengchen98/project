package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户等级
 * @author jinlei
 * @date 2021-12-21 00:15:58
 */
@Data
@TableName("user_level")
public class UserLevelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 等级名称
	 */
	private String name;
	/**
	 * 等级需要的成长值
	 */
	private Integer growthPoint;
	/**
	 * 是否为默认等级[0->不是；1->是]
	 */
	private Integer defaultStatus;
	/**
	 * 免运费标准
	 */
	private BigDecimal freeFreightPoint;
	/**
	 * 每次评价获取的成长值
	 */
	private Integer commentGrowthPoint;
	/**
	 * 是否有免邮特权
	 */
	private Integer priviledgeFreeFreight;
	/**
	 * 是否有会员价格特权
	 */
	private Integer priviledgeMemberPrice;
	/**
	 * 是否有生日特权
	 */
	private Integer priviledgeBirthday;
	/**
	 * 备注
	 */
	private String note;

}
