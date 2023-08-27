package com.vteam.vtarm.task;

import java.lang.annotation.*;

/**
 * 定时任务执行日志 .<br>
 *
 * @author andy.sher
 * @date 2019/5/24 9:45
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TaskExecuteLog {

    /**
     * 日志描述 .
     *
     * @param
     * @return java.lang.String 日志描述
     * @author andy.sher
     * @date 2019/5/24 9:46
     */
    String value();

}
