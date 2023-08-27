package com.vteam.vtarm.transaction;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@ConditionalOnMissingBean(DataSourceTransactionManager.class)
@AutoConfigureAfter({MybatisAutoConfiguration.class, SqlSessionFactory.class})
@EnableConfigurationProperties(TransactionProperties.class)
@Configuration
public class DataSourceTransactionManagerConfigure {

    private final DataSource dataSource;

    public DataSourceTransactionManagerConfigure(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @ConditionalOnMissingBean(DataSourceTransactionManager.class)
    @Bean("dataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

}
