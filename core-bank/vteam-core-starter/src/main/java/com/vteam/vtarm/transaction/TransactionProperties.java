package com.vteam.vtarm.transaction;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 事务配置类 .<br>
 *
 * @author andy.sher
 * @date 2019/12/3 9:04
 */
@Data
@ConfigurationProperties(prefix = "spring.vtarm.transaction")
public class TransactionProperties {

    private int timeout;

}
