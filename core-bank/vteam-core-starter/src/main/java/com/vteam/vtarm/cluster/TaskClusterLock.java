package com.vteam.vtarm.cluster;

import com.vteam.vtarm.task.TaskExecuteLog;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.*;

/**
 * 声明为集群锁的注解 .<br>
 *
 * @author andy.sher
 * @date 2019/5/17 9:33
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TaskClusterLock {

    String value() default "";

    /**
     * 过期时间 . <br>
     * 注意：过期时间表示分布式锁的有效时间，建议大于任务的执行周期，该时间保证了节点宕机后的锁自动过期时间
     *
     * @param
     * @return 过期时间
     * @author andy.sher
     * @date 2019/5/17 9:34
     */
    int expire() default 1800;

    /**
     * 日志配置 .
     *
     * @param
     * @return com.vteam.vtarm.task.TaskExecuteLog 日志配置
     * @author andy.sher
     * @date 2019/5/24 16:57
     */
    TaskExecuteLog logger() default @TaskExecuteLog(StringUtils.EMPTY);

}
