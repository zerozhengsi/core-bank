package com.vteam.cars.plugin.remote.config;

import com.vteam.cars.config.SmeConfiguration;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.Resource;

/**
 * 连接保持策略
 *
 * @author zack.yin
 * @date 2018/7/12 9:06
 */
@Configuration
public class ConnectionKeepAliveStrategyConfig {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Bean("connectionKeepAliveStrategy")
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return (HttpResponse response, HttpContext context) -> {
            HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && "timeout".equalsIgnoreCase(param)) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (NumberFormatException ignore) {
                    }
                }
            }
            return smeConfiguration.getKeepAliveTime();
        };
    }
}