package com.shenzhou.logistics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @ClassName UploadFileConfig
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/12 12:11
 * @Version 1.0
 */
@Configuration
public class UploadFileConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setLocation(uploadFolder);
        //文件最大
        factory.setMaxFileSize(DataSize.parse("5MB"));
        // 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("10MB"));
        return factory.createMultipartConfig();
    }

}
