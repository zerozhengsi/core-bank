package com.vteam.vtarm.sql;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

/**
 * SQL适配器配置 .<br>
 *
 * @author andy.sher
 * @date 2019/12/2 15:28
 */
@AutoConfigureAfter(SqlSessionFactory.class)
@EnableConfigurationProperties(SqlProperties.class)
@Configuration
public class SqlAutoConfigure {

    private final SqlSessionFactory sqlSessionFactory;

    public SqlAutoConfigure(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * 最后加入SQL适配拦截器
     */
    @PostConstruct
    public void addInterceptor() {
        Interceptor interceptor = new SqlInterceptor();
        sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
    }

    @Bean
    public HandlerManager handlerManager() {
        return new HandlerManager();
    }

}
