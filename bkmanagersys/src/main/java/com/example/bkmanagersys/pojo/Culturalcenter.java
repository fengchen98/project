package com.example.bkmanagersys.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: bkmanager
 * @description:
 * @author: FENG CHEN
 * @create: 2021-12-27 10:47
 */
@Data
@TableName("culturalcenter")

public class Culturalcenter {
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
    private String province;
    private String city;
    private String county;
}
