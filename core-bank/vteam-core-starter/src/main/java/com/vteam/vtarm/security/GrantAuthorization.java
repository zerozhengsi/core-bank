package com.vteam.vtarm.security;

import java.lang.annotation.*;

/**
 * 此注解用于声明在Controller 上面，被声明的Controller方法表示一个授权请求，将会对用户进行授权操作 .<br>
 *
 * @author andy.sher
 * @date 2019/4/19 9:18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GrantAuthorization {

    /**
     * 响应对象中用户信息的键 .
     *
     * @param
     * @return java.lang.String 用户信息的键
     * @author andy.sher
     * @date 2019/4/19 9:24
     */
    String value() default "userInfo";

    /**
     * 是否为临时授权，临时授权会生成一个有效期只有5分钟的token，适用于客户端连续的调用多个接口时，保证每一次调用的合法性 .
     *
     * @param
     * @return boolean 是否为临时授权
     * @author andy.sher
     * @date 2019/5/27 11:23
     */
    boolean temporarily() default false;

    /**
     * 授权码作用域 .
     *
     * @param
     * @return java.lang.String 授权码作用域
     * @author andy.sher
     * @date 2019/12/10 17:05
     */
    String scope() default "";

}
