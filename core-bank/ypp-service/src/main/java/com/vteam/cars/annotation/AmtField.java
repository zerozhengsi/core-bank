package com.vteam.cars.annotation;

import java.lang.annotation.*;

/**
 * 金额字段 .<br>
 *
 * @author andy.sher
 * @date 2018/10/24 10:52
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AmtField {

    /**
     * 金额字段数组 .
     *
     * @return java.lang.String[] 字段数组
     * @author andy.sher
     * @date 2019/1/9 14:46
     */
    String[] fields();

}
