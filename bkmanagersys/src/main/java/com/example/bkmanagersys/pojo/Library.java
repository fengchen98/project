package com.example.bkmanagersys.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;

/**
 * @program: bkmanager
 * @description:
 * @author: FENG CHEN
 * @create: 2021-12-22 13:12
 */
@Data
@TableName("library")

public class Library {
    private int id;
    private String name;
    private String agencycode;
    private String agencynumber;
    private String areaname;
    private String postalcode;
    private String tel;
    private String tradecode;
    private String address;
    private String registertype;
    private String groups;
    private String level;
    private String status;
    private String ischildrenlibrary;
    private String province;
    private String city;
    private String county;
    private String venueresourceid;
    private String belongcode;
    private int isShow;
    private String coordinate;
}
