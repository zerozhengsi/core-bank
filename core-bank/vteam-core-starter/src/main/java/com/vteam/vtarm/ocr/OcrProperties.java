package com.vteam.vtarm.ocr;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 图片文字识别配置类 .<br>
 *
 * @author andy.sher
 * @date 2019/12/2 11:38
 */
@Data
@ConfigurationProperties(prefix = "spring.vtarm.ocr")
public class OcrProperties {

    private String appId;

    private String apiKey;

    private String secretKey;

}
