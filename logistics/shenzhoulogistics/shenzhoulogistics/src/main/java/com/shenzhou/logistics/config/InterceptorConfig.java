package com.shenzhou.logistics.config;


import com.shenzhou.logistics.Interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 拦截器配置
 * @Date: 2022/1/23
 * @Time: 23:04
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer  {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
    }
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
//        System.out.println("创建authenticationInterceptor过滤器");
        return new AuthenticationInterceptor();
    }

}
