package com.example.bkmanagersys.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: bkmanager
 * @description:
 * @author: FENG CHEN
 * @create: 2021-12-24 10:07
 */
@Data
@TableName("township")

public class Township {
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
    private String province;
    private String city;
    private String county;
    private String distinguish;
    private String subjection;
}
