package com.vteam.vtarm.ocr;

import com.baidu.aip.ocr.AipOcr;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.Resource;

/**
 * 图片文字识别配置 .<br>
 *
 * @author andy.sher
 * @date 2019/12/2 11:38
 */
@EnableConfigurationProperties(OcrProperties.class)
@Configuration
public class OcrAutoConfigure {

    @Resource
    private OcrProperties ocrProperties;

    @Bean
    public AipOcr aipOcr() {
        AipOcr aipOcr = new AipOcr(ocrProperties.getAppId(), ocrProperties.getApiKey(), ocrProperties.getSecretKey());
        // 可选：设置网络连接参数
        aipOcr.setConnectionTimeoutInMillis(2000);
        aipOcr.setSocketTimeoutInMillis(60000);
        return aipOcr;
    }

    @Bean
    public OcrResolveExecutor ocrResolveExecutor() {
        return new OcrResolveExecutor();
    }

}
