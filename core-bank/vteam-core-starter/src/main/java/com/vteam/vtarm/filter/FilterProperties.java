package com.vteam.vtarm.filter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 过滤器配置类 .<br>
 *
 * @author andy.sher
 * @date 2019/11/21 13:04
 */
@Data
@ConfigurationProperties(prefix = "spring.vtarm.filter")
public class FilterProperties {

    /**
     * 忽略过滤的资源路径
     */
    private String[] ignoreUrl;

}
