package com.vteam.vtarm.control;

import java.lang.annotation.*;

/**
 * 接口开关 .<br>
 *
 * @author andy.sher
 * @date 2019/9/11 11:03
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiSwitch {

    /**
     * 返回值是否是数组 .
     *
     * @param
     * @return boolean
     * @author andy.sher
     * @date 2019/9/11 11:04
     */
    boolean isArray() default false;

    /**
     * 控制器（多个控制器下如果有一个是已关闭的状态，则拒绝访问） .
     *
     * @param
     * @return java.lang.Class<?> 控制器
     * @author andy.sher
     * @date 2019/9/11 11:06
     */
    Class<? extends ApiSwitchControl>[] controls();

    /**
     * 根节点名称 .
     *
     * @param
     * @return java.lang.String 根节点名称
     * @author andy.sher
     * @date 2019/9/11 11:07
     */
    String root() default "";

}
