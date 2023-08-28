package com.vteam.cars.annotation;

import java.lang.annotation.*;

/**
 * 服务接口日志 .<br>
 *
 * @author andy.sher
 * @date 2019/9/29 11:51
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceAPILog {

    /**
     * 接口唯一标识 .
     *
     * @param
     * @return java.lang.String 接口唯一标识
     * @author andy.sher
     * @date 2019/9/29 11:52
     */
    String route();

    /**
     * 接口名称 .
     *
     * @param
     * @return java.lang.String 接口名称
     * @author andy.sher
     * @date 2019/9/29 11:52
     */
    String name();

    /**
     * 不记录日志的参数下标（从0开始） .
     *
     * @param
     * @return java.lang.String[] 不记录的参数下标
     * @author andy.sher
     * @date 2019/9/29 13:22
     */
    int[] ignore() default {};

    /**
     * 是否记录错误日志（默认为是）.
     * @author toby.tang
     * @date 2023/2/24 14:00
     */
    boolean errorLog() default true;

}
