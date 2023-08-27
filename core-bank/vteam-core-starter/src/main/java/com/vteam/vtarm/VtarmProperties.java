package com.vteam.vtarm;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 框架配置类 .<br>
 *
 * @author andy.sher
 * @date 2019/12/2 18:37
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.vtarm")
public class VtarmProperties {

    private boolean developerMode;

}
