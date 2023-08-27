package com.vteam.cars.plugin.file.upload.config;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.plugin.license.Authorization;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import jakarta.annotation.Resource;
import jakarta.servlet.MultipartConfigElement;

/**
 * 文件上传配置 .<br>
 *
 * @author andy.sher
 * @date 2018/7/10 17:23
 */
//@Configuration
public class MultipartConfig {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    public MultipartConfig() {
        super();
        // 增加许可证认证控制
        boolean hasAuthorized = Authorization.HAS_AUTHORIZED;
        if (!hasAuthorized) {
            System.exit(0);
            return;
        }
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制 ,超出此大小页面会抛出异常信息KB,MB
        factory.setMaxFileSize(DataSize.parse("2000MB"));
        // 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("2000MB"));
        // 设置文件临时文件夹路径
        factory.setLocation(smeConfiguration.getTmpPath());
        return factory.createMultipartConfig();
    }

}
