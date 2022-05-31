package com.shenzhou.logistics.Exception;

/**
 * @Description:
 * @author jinlei
 * @createTime: 2021-12-27 16:04
 **/
public class EmailException extends RuntimeException {
    public EmailException() {
        super("存在相同的邮箱");
    }
}
