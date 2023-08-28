package com.vteam.cars.plugin.remote.config;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.plugin.license.Authorization;
import org.apache.http.client.config.RequestConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.Resource;

/**
 * 设置请求的各种配置
 *
 * @author zack.yin
 * @date 2018/7/12 9:12
 */
@Configuration
public class HttpRequestConfig {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    public HttpRequestConfig() {
        super();
        // 增加许可证认证控制
        boolean hasAuthorized = Authorization.HAS_AUTHORIZED;
        if (!hasAuthorized) {
            System.exit(0);
            return;
        }
    }

    @Bean
    public RequestConfig config() {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(smeConfiguration.getConnectRequestTimeout())
                .setConnectTimeout(smeConfiguration.getConnectTimeout())
                .setSocketTimeout(smeConfiguration.getSocketTimeout())
                .build();
    }
}
