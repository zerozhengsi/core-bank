package com.vteam.cars.plugin.weixin.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 微信配置 .<br>
 *
 * @author andy.sher
 * @date 2019/11/27 13:29
 */
@Data
@Component
@ConfigurationProperties(prefix = "application.weixin")
public class WeixinProperties {

    private Map<String, String> clients;

}
