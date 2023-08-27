package com.vteam.vtarm.task;

import com.vteam.vtarm.CommonConstants;
import com.vteam.vtarm.thread.ThreadPoolConfiguration;
import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 任务配置 .<br>
 *
 * @author andy.sher
 * @date 2019/12/2 12:44
 */
@Slf4j
@AutoConfigureAfter({ThreadPoolConfiguration.class})
@EnableConfigurationProperties(TaskProperties.class)
@EnableScheduling
@Configuration
public class TaskAutoConfigure implements SchedulingConfigurer {

    private final TaskProperties taskProperties;

    private final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    public TaskAutoConfigure(TaskProperties taskProperties, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        this.taskProperties = taskProperties;
        this.scheduledThreadPoolExecutor = scheduledThreadPoolExecutor;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(scheduledThreadPoolExecutor);
        Map<String, Task> taskMap = SpringContextUtils.getBeansOfType(Task.class);
        taskProperties.getCrons().entrySet().forEach(e -> {
            if (e.getKey().contains(CommonConstants.Symbol.MINUS)) {
                taskMap.remove((e.getKey().replace(CommonConstants.Symbol.MINUS, StringUtils.EMPTY).trim()));
                log.info("定时任务[{}]未激活。", e.getKey());
            }
        });
        taskMap.entrySet().forEach(e -> {
            String key = e.getKey();
            String cronexp = taskProperties.getCrons().get(key);
            scheduledTaskRegistrar.addCronTask(new CronTask(e.getValue(), new CronTrigger(cronexp)));
            log.info("定时任务[{}]已激活，Cron表达式[{}]。", key, cronexp);
        });
    }

}
