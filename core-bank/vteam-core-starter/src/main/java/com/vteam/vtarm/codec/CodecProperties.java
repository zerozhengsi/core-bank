package com.vteam.vtarm.codec;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 编解码配置类 .<br>
 *
 * @author andy.sher
 * @date 2019/11/29 17:53
 */
@Data
@ConfigurationProperties(prefix = "spring.vtarm.codec")
public class CodecProperties {

    private boolean apiEnable;

    private boolean dbEnable;

    private String aesKey;

    private String aesIv;

    private String[] ignoreUrl;

}
