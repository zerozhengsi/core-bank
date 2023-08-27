package com.vteam.cars.plugin.mybatis.config;

import com.vteam.cars.plugin.license.Authorization;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * MyBatis配置 .<br>
 *
 * @author andy.sher
 * @date 2018/7/12 14:46
 */
@MapperScan(basePackages = {"com.vteam.cars.entity.mapper", "com.vteam.cars.sql","com.vteam.cars.entity.extendmapper"})
@Configuration
public class MyBatisConfiguration {

    public MyBatisConfiguration() {
        super();
        // 增加许可证认证控制
        boolean hasAuthorized = Authorization.HAS_AUTHORIZED;
        if (!hasAuthorized) {
            System.exit(0);
            return;
        }
    }

    /**
     * 自动识别使用的数据库类型 <br/>
     * 在mapper.xml中databaseId的值就是跟这里对应，如果没有databaseId选择则说明该sql适用所有数据库
     * @return DatabaseIdProvider
     * @author oscar.yu
     * @date 2020/1/7 10:17
     */
    @Bean
    public DatabaseIdProvider databaseIdProvider() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties properties = new Properties();
        properties.setProperty("Oracle", "oracle");
        properties.setProperty("MySQL", "mysql");
        properties.setProperty("DB2", "db2");
        properties.setProperty("SQL Server", "sqlserver");
        // 浪潮KDB数据库
        properties.setProperty("Inspur K-DB", "kdb");
        databaseIdProvider.setProperties(properties);
        return databaseIdProvider;
    }

}
