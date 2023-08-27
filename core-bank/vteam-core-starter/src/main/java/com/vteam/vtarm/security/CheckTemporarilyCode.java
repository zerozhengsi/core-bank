package com.vteam.vtarm.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 此注解用于声明在Controller 上面，被声明的Controller方法表示需要校验临时授权码的合法性 .<br>
 *
 * @author andy.sher
 * @date 2019/4/19 9:18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckTemporarilyCode {
    
    /**
     * 临时授权码的键 .
     * 
     * @return java.lang.String 授权码的键
     * @author oscar.yu
     * @date 2019/12/10 19:08
     */
    String uuid() default "uuid";

    /**
     * 授权码作用域（标记业务字段） .
     * 
     * @return java.lang.String 授权码作用域
     * @author oscar.yu
     * @date 2019/12/10 19:06
     */
    String scope() default "";
    
}
