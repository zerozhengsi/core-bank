package com.vteam.cars.plugin.activemq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @className: ActivemqProperties
 * @Description: TODO
 * @version: v1.８.0
 * @author: care.zheng
 * @date: 2023/8/21 15:04
 */
@Data
@ConfigurationProperties(prefix = "spring.activemq")
public class ActivemqProperties {
    private String brokerUrl;

    private String user;

    private String password;
}
