package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //  全参构造器
@NoArgsConstructor  //  无参构造器
@Data   //  lombok会帮助自动生成对应方法
@TableName("carousel")  //  指定是数据库里面哪张表

public class CarouselEntity {

    @TableId//指定主键递增
    private Integer id;

    private String title;

    private String mark;

    private String images;

    private String status;

    private String filename;

    private String create_time;

    private String is_delete;

}
