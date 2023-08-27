package com.vteam.vtarm.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置 .<br>
 *
 * @author andy.sher
 * @date 2018/10/10 9:00
 */
@Slf4j
@Configuration
public class ThreadPoolConfiguration {

    /**
     * 定时任务线程池 .
     *
     * @param
     * @return java.util.concurrent.ScheduledThreadPoolExecutor 定时任务线程池
     * @author andy.sher
     * @date 2019/12/2 14:58
     */
    @Bean(destroyMethod = "shutdown")
    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        // 设置核心线程数
        executor.setCorePoolSize(5);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveTime(1800, TimeUnit.SECONDS);
        // 设置最大线程数
        executor.setMaximumPoolSize(10);
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        log.info("成功初始化定时任务线程池配置");
        return executor;
    }

    /**
     * 任务处理器 .
     *
     * @param
     * @return org.springframework.core.task.TaskExecutor
     * @author andy.sher
     * @date 2019/1/9 14:57
     */
    @Bean
    public TaskExecutor taskExecutor() {
        return getTaskExecutor(3, 5, 10, 30, "DefaultTask-", "成功初始化默认线程池配置");
    }

    /**
     * 任务处理器 .
     *
     * @param
     * @return org.springframework.core.task.TaskExecutor
     * @author andy.sher
     * @date 2019/1/9 14:57
     */
    @Bean(name = "smeTaskExecutor")
    public TaskExecutor smeTaskExecutor() {
        return getTaskExecutor(5, 10, 20, 60, "SmeTask-", "成功初始化线程池配置");
    }

    /**
     * 按参数获取任务执行器 .
     *
     * @param i   核心线程数
     * @param i2  最大线程数
     * @param i3  队列容量
     * @param i4  线程活跃时间（秒）
     * @param s   默认线程名称
     * @param msg 消息
     * @return org.springframework.core.task.TaskExecutor
     * @author andy.sher
     * @date 2019/6/14 13:57
     */
    private TaskExecutor getTaskExecutor(int i, int i2, int i3, int i4, String s, String msg) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(i);
        // 设置最大线程数
        executor.setMaxPoolSize(i2);
        // 设置队列容量
        executor.setQueueCapacity(i3);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(i4);
        // 设置默认线程名称
        executor.setThreadNamePrefix(s);
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        log.info(msg);
        return executor;
    }

    /**
     * 导出任务处理器 .
     *
     * @param
     * @return org.springframework.core.task.TaskExecutor
     * @author andy.sher
     * @date 2019/5/10 13:57
     */
    @Bean(name = "exportTaskExecutor")
    public TaskExecutor exportTaskExecutor() {
        return this.getTaskExecutor(5, 10, 5, 600, "ExportTask-", "成功初始化导出任务线程池配置");
    }

    /**
     * 注册调度器执行工具 .
     *
     * @param
     * @return java.util.concurrent.ScheduledExecutorService 调度器执行工具
     * @author andy.sher
     * @date 2019/5/24 18:11
     */
    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(5, new BasicThreadFactory.Builder().namingPattern("release-cluster-lock-%d").daemon(true).build());
        return scheduledExecutorService;
    }

}
