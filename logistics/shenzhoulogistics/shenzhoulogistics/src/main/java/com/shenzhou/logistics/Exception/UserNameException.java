package com.shenzhou.logistics.Exception;

/**
 * @Description:
 * @author jinlei
 * @createTime: 2021-12-27 16:04
 **/
public class UserNameException extends RuntimeException {
    public UserNameException() {
        super("存在相同的用户名");
    }
}
