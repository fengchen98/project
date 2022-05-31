package com.shenzhou.logistics.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @Description:
 * @author jinlei
 * @createTime: 2021-12-27 19:29
 **/
@CrossOrigin(origins = "*")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLoginVo {

    private String username;
    private String password;

}
