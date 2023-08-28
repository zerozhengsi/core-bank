package com.vteam.cars.plugin.message.weixin.config;

import com.vteam.cars.plugin.message.weixin.support.MyX509TrustManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信推送消息类 .<br>
 *
 * @author andy.sher
 * @date 2018/9/28 10:28
 */
@Configuration
public class WxConfiguration {

    @Bean
    public MyX509TrustManager myX509TrustManager() {
        return new MyX509TrustManager();
    }

}
