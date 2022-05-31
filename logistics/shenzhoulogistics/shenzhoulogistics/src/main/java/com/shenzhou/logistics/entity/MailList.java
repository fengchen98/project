package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor//全参构造器
@NoArgsConstructor//无参构造器
@Data//lombok会帮助自动生成对应方法
@TableName("mail_list")//指定是数据库里面哪张表
public class MailList {
    @TableId//指定主键递增
    private Integer id;
    private String service;
    private String type;
    private String icon;
    private String link;
    private String more;
    private String tag;

}
