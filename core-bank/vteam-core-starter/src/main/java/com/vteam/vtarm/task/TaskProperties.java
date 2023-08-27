package com.vteam.vtarm.task;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 任务配置 .<br>
 *
 * @author andy.sher
 * @date 2019/12/2 13:01
 */
@Data
@ConfigurationProperties(prefix = "spring.vtarm.task")
public class TaskProperties {

    /**
     * 任务执行表达式组
     */
    private Map<String, String> crons;

}
