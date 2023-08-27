package com.vteam.vtarm.codec;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据安全配置 .<br>
 *
 * @author andy.sher
 * @date 2019/3/26 13:02
 */
@Slf4j
@EnableConfigurationProperties(CodecProperties.class)
@Configuration
public class CodecAutoConfigure {

    /**
     * 数据安全查询拦截器 .<br>
     *
     * @param
     * @return org.apache.ibatis.plugin.Interceptor 数据安全查询拦截器
     * @author andy.sher
     * @date 2018/8/14 09:38
     */
    @Bean
    public Interceptor codecInterceptor() {
        return new CodecInterceptor();
    }

    @Bean
    public DataCodecExecutor dataCodecExecutor() {
        return new DataCodecExecutor();
    }

    @Bean
    public DataCodecStrategyGroup dataCodecStrategyGroup() {
        return new DataCodecStrategyGroup();
    }

    @Bean
    public DataSecurityAspect dataSecurityAspect() {
        return new DataSecurityAspect();
    }

}
