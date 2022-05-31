package com.shenzhou.logistics.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.shenzhou.logistics.config.AlipayConfig;
import com.shenzhou.logistics.entity.AlipayEntity;
import com.shenzhou.logistics.qrcode.QRCodeUtil;
import com.shenzhou.logistics.qrcode.QrCodeResponse;
import com.shenzhou.logistics.qrcode.QrResponse;
import com.shenzhou.logistics.service.PayService;
import com.shenzhou.logistics.utils.AlipayUtil;
import com.shenzhou.logistics.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

/**
 * @ClassName PayServiceImpl
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/15 8:18
 * @Version 1.0
 */
@Service
public class PayServiceImpl implements PayService {

//    @Override
//    public String aliPay(AlipayEntity alipayEntity) throws AlipayApiException {
//        return AlipayUtil.connect(alipayEntity);
//    }

    @Autowired
    private AlipayConfig alipayConfig;

    @Override
    public byte[] alipy() {
        try {
            // 业务数据
            String userId = "5";
            String orderNumber = UUID.randomUUID().toString().replaceAll("-", "");
            String money = "55";
            String title = "测试支付";

            // 回调参数
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", userId);
            jsonObject.put("orderNumber", orderNumber);
            jsonObject.put("money", money);
            String param = jsonObject.toString();

            // 配置支付信息
            AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
            model.setOutTradeNo(orderNumber);   // 订单号
            model.setTotalAmount(money);    // 金额
            model.setSubject(title);    // 订单名称
            model.setBody(param);   // 回调参数
            model.setTimeoutExpress("30m"); // 有效时间
            model.setStoreId(userId);   // 用户id
            QrCodeResponse qrCodeResponse = qrcodePay(model);

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            String logoPath = "";
//            try {
//                logoPath = ResourceUtils.getFile("D:\\JetBrains\\IDEA\\workspace\\shenzhoulogistics\\target\\classes\\static\\uploadImages\\default\\favicon.ico").getAbsolutePath();
//            }catch (Exception ex){
//                logoPath = new java.io.File("/var/www/html/shenzhou/dist/favicon.ico").getAbsolutePath();
//            }
            BufferedImage buffImg = QRCodeUtil.encode(qrCodeResponse.getQr_code(), logoPath, false);//获取二维码
            ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
            ImageIO.write(buffImg, "JPEG", imageOut);
            imageOut.close();
            ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
            return FileCopyUtils.copyToByteArray(input);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public byte[] alipy(AlipayEntity alipayEntity) {
        try {
            // 业务数据
            String userId = alipayEntity.getUser_id();
            String userName = alipayEntity.getUser_name();
            String orderNumber = alipayEntity.getOut_trade_no();
            String money = alipayEntity.getTotal_amount();
            String title = alipayEntity.getSubject();
            String ptype = alipayEntity.getPtype();

            // 回调参数
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", userId);
            jsonObject.put("username", userName);
            jsonObject.put("orderNumber", orderNumber);
            jsonObject.put("money", money);
            jsonObject.put("ptype", ptype); // 支付类型，用于区分多种支付场景
            String param = jsonObject.toString();
            System.out.println("回调参数：" + param);
            // 配置支付信息
            AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
            model.setOutTradeNo(orderNumber);   // 订单号
            model.setTotalAmount(money);    // 金额
            model.setSubject(title);    // 订单名称
            model.setBody(param);   // 回调参数
            model.setTimeoutExpress("30m"); // 有效时间
            model.setStoreId(userId);   // 用户id
            QrCodeResponse qrCodeResponse = qrcodePay(model);
            System.out.println("收到qrCodeResponse" + qrCodeResponse);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            String logoPath = "";
//            try {
//                logoPath = ResourceUtils.getFile("D:\\JetBrains\\IDEA\\workspace\\shenzhoulogistics\\target\\classes\\static\\uploadImages\\default\\favicon.ico").getAbsolutePath();
//            }catch (Exception ex){
//                logoPath = new java.io.File("/var/www/html/shenzhou/dist/favicon.ico").getAbsolutePath();
//            }
            BufferedImage buffImg = QRCodeUtil.encode(qrCodeResponse.getQr_code(), logoPath, false);//获取二维码
            ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
            ImageIO.write(buffImg, "JPEG", imageOut);
            imageOut.close();
            ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
            return FileCopyUtils.copyToByteArray(input);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public QrCodeResponse qrcodePay(AlipayTradePrecreateModel model){
        // 拉取请求客户端
        AlipayClient alipayClient = getAlipayClient();
        // 获取请求对象
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        // 设置请求参数
        request.setBizModel(model);
        // 设置异步回调地址
        request.setNotifyUrl(alipayConfig.getNotify_url());
        // 设置同步回调地址
        request.setReturnUrl(alipayConfig.getReturn_url());
        AlipayTradePrecreateResponse alipayTradePrecreateResponse = null;
        try {
            alipayTradePrecreateResponse = alipayClient.execute(request);
            System.out.println("alipayTradePrecreateResponse" + JSONObject.toJSONString(alipayTradePrecreateResponse));
        } catch (AlipayApiException e) {
            System.out.println("qrcodePay抛出错误");
            e.printStackTrace();
        }
        assert alipayTradePrecreateResponse != null;
        QrResponse qrResponse = JSON.parseObject(alipayTradePrecreateResponse.getBody(), QrResponse.class);
        return qrResponse.getAlipay_trade_precreate_response();
    }

    private AlipayClient getAlipayClient() {
        return new DefaultAlipayClient(alipayConfig.getGatewayUrl(), alipayConfig.getApp_id(), alipayConfig.getMerchant_private_key(),
        "JSON", alipayConfig.getCharset(), alipayConfig.getAlipay_public_key(), alipayConfig.getSign_type());
    }
}
