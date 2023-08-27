package com.vteam.vtarm.sql;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * sql配置类 .<br>
 *
 * @author andy.sher
 * @date 2019/12/3 9:07
 */
@Data
@ConfigurationProperties(prefix = "spring.vtarm.sql")
public class SqlProperties {

    private Type type;

    /**
     * 数据库类型 .
     *
     * @author andy.sher
     * @date 2019/12/3 9:08
     */
    public enum Type {
        MYSQL, ORACLE, MSSQL, DB2, KDB, DEFAULT
    }

}
