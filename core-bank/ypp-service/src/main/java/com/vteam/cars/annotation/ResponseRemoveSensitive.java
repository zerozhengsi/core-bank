package com.vteam.cars.annotation;

import java.lang.annotation.*;

/**
 * 响应内容脱敏 .<br>
 *
 * @author andy.sher
 * @date 2019/9/9 14:44
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseRemoveSensitive {

    /**
     * 响应数据是否是列表或者数组（默认为否） .
     *
     * @param
     * @return boolean
     * @author andy.sher
     * @date 2019/9/9 15:00
     */
    boolean multiple() default false;

    /**
     * 脱敏的目标对象（data数据没有根节点时请设置为空字符串） .
     *
     * @param
     * @return java.lang.String
     * @author andy.sher
     * @date 2019/9/9 15:19
     */
    String target() default "";

    /**
     * 对象类型（必须是注解 @Sensitive 的VO类） .
     *
     * @param
     * @return java.lang.Class<?> 对象类型
     * @author andy.sher
     * @date 2019/9/9 15:46
     */
    Class<?> type();

}
