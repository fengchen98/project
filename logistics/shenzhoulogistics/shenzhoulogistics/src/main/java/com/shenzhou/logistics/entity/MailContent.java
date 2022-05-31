package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor//全参构造器
@NoArgsConstructor//无参构造器
@Data//lombok会帮助自动生成对应方法
@TableName("mail_content")//指定是数据库里面哪张表
public class MailContent {
    @TableId//指定主键递增
    private Integer id;
    private String type;
    private String service;
    private String title;
    private String icon;
    private String more;
    private String link;
    private String tag;
    private String imgSrc;
    private String into_title1;
    private String into_info1;
    private String into_title2;
    private String into_info2;
    private String into_title3;
    private String into_info3;
    private String into_title4;
    private String into_info4;
    private String into_title5;
    private String into_info5;

}
