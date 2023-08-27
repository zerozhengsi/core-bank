package com.vteam.cars.annotation;

import java.lang.annotation.*;

/**
 * 支持脱敏的注解 .<br>
 *
 * @author andy.sher
 * @date 2019/9/9 13:50
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sensitive {

    /**
     * 需要脱敏的字段数组 .
     *
     * @param
     * @return java.lang.String[] 需要脱敏的字段数组
     * @author andy.sher
     * @date 2019/9/9 13:52
     */
    String[] fields();

}
