package com.shenzhou.logistics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName WebMvcConfig
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/12 12:07
 * @Version 1.0
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

//    @Value("${file.staticAccessPath}")
//    private String staticAccessPath;
//
//    @Value("${file.uploadFolder}")
//    private String uploadFolder;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploadImages/**").addResourceLocations("classpath:/static/uploadImages/","file:/home/andre/backend/static/uploadImages/");


        // 配置 服务器上的地址 localhost:8080/Users/zlf/myBlog/upload/ 映射到本地linux/Mac  /Users/zlf/myBlog/upload/ (需要在前面加 file:)
//        registry.addResourceHandler(staticAccessPath+"**").addResourceLocations("file:"+uploadFolder);
        super.addResourceHandlers(registry);

    }
}
