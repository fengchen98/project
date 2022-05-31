package com.shenzhou.logistics.Exception;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description:
 * @Date: 2021/12/22
 * @Time: 21:58
 **/
/**
 * 错误码和错误信息定义类
 * @author jinlei
 */
public enum BizCodeEnum {

    /**
     * 系统未知异常
     */
    UNKNOWN_EXCEPTION(10000, "系统未知异常"),
    /**
     * 参数校验错误
     */
    VALID_EXCEPTION(10001, "参数格式校验失败"),

    TO_MANY_REQUEST(10002, "请求流量过大，请稍后再试"),
    SMS_CODE_EXCEPTION(10002, "验证码获取频率太高，请稍后再试"),
    MAIL_CODE_EXCEPTION(10003, "邮箱验证码获取频率太高，请稍后再试"),
    PRODUCT_UP_EXCEPTION(11000, "商品上架异常"),
    USER_EXIST_EXCEPTION(15001, "存在相同的用户"),
    EMAIL_EXIST_EXCEPTION(15002, "存在相同的邮箱"),
    EMAIL_CODE_INCORRECT_EXCEPTION(15004, "邮箱验证码错误"),
    NO_STOCK_EXCEPTION(21000, "商品库存不足"),
    LOGIN_ACCOUNT_PASSWORD_EXCEPTION(15003, "账号或密码错误"),
    ACCOUNT_MISMATCH_EMAIL_EXCEPTION(15005, "账号与邮箱不匹配"),
    ORDER_CREATE_EXCEPTION(15006, "订单创建异常"),
    TOKEN_EXPIRED_EXCEPTION(14004, "用户登录状态已过期，请重新登录"),
    PERMISSIONS_DENIED_EXCEPTION(14005, "用户权限不足"),
    ;

    private final int code;
    private final String msg;

    BizCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
