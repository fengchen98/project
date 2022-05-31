package com.shenzhou.logistics.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author jinlei
 * @createTime: 2021-12-27 15:37
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterVo {

    private String userName;
    private String email;
    private String emailCode;
    private String password;
    private String code;

}
