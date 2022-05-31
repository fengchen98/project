package com.shenzhou.logistics.config;

import com.alipay.api.DefaultAlipayClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 支付宝配置
 * @Date: 2022/3/22
 * @Time: 0:17
 **/
@Configuration
@Data
public class AlipayConfig {


    @Value("${alipay.app_id}")
    public String app_id;

    @Value("${alipay.private_key}")
    public String merchant_private_key;

    @Value("${alipay.public_key}")
    public String alipay_public_key;

    @Value("${alipay.notify_url}")
    public String notify_url;

    @Value("${alipay.return_url}")
    public String return_url;

    @Value("${alipay.sign_type}")
    public String sign_type;

    @Value("${alipay.charset}")
    public String charset;

    @Value("${alipay.gatewayUrl}")
    public String gatewayUrl;

}
