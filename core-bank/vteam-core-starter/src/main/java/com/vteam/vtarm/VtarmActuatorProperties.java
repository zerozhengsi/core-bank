package com.vteam.vtarm;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * actuator 端点配置 .<br>
 *
 * @author andy.sher
 * @date 2020/4/21 17:34
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.vtarm.actuator")
public class VtarmActuatorProperties {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
