package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName recipient
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/10 7:38
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("recipient")
public class RecipientEntity {

    @TableId
    private String id;

    private String userid;

    private String name;

    private String phone;

    private String area;

    private String address;

    private String postcode;

    private String idnumber;

    private boolean isconfirm;

    private boolean isdefault;

    private String remark;

}
