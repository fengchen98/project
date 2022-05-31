package com.shenzhou.logistics.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jinlei
 * @date 2022-01-23 23:50:32
 */
@ConfigurationProperties(prefix = "logistics.thread")
@Data
public class ThreadPoolConfigProperties {

    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;

}
