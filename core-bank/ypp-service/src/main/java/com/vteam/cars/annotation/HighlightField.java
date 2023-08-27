package com.vteam.cars.annotation;

import java.lang.annotation.*;

/**
 * 高亮字段 .<br>
 *
 * @author andy.sher
 * @date 2018/11/29 16:59
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HighlightField {

    /**
     * 高亮字段数组 .
     *
     * @param
     * @return java.lang.String[] 高亮字段数组
     * @author andy.sher
     * @date 2019/1/9 14:47
     */
    String[] fields();

}
