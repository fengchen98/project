package com.shenzhou.logistics.service;

import com.alipay.api.AlipayApiException;
import com.shenzhou.logistics.entity.AlipayEntity;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 支付service
 * @Date: 2022/3/22
 * @Time: 0:23
 **/
public interface PayService {
//    String aliPay(AlipayEntity alipayEntity) throws AlipayApiException;

    byte[] alipy();

    byte[] alipy(AlipayEntity alipayEntity);


}
