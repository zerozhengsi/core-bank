package com.vteam.vtarm.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 安控框架属性配置 .<br>
 *
 * @author andy.sher
 * @date 2019/11/28 11:49
 */
@Data
@ConfigurationProperties("spring.vtarm.security")
public class SecurityProperties {

    /**
     * 安全级别
     */
    private LEVEL level;

    /**
     * 不受控制的资源路径
     */
    private String ignoreUrls;

    /**
     * 允许重放的资源
     */
    private String[] allowReplayUrls;

    /**
     * access_token登录会话有效时长(分钟)
     */
    private Integer timeToLive;

    /**
     * 安全级别 .
     *
     * @author andy.sher
     * @date 2019/11/29 16:29
     */
    public enum LEVEL {
        NORMAL, HIGH
    }

}
