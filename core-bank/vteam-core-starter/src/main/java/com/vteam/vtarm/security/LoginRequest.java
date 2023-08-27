package com.vteam.vtarm.security;

import java.lang.annotation.*;

/**
 * 此注解用于声明在Controller 上面，被声明的Controller方法表示一个登录请求，将在响应对象中注入用户认证信息 .<br>
 *
 * @author andy.sher
 * @date 2019/4/19 9:18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequest {

    /**
     * 响应对象中用户信息的键 .
     *
     * @param
     * @return java.lang.String 用户信息的键
     * @author andy.sher
     * @date 2019/4/19 9:24
     */
    String value();

    /**
     * 是否授权(默认为否)
     *
     * @return
     * @author andy.sher
     * @date 2019/7/29 16:43
     */
    boolean authorize() default false;

    /**
     * 登录是否需要凭证 .
     *
     * @param
     * @return boolean 默认为否
     * @author andy.sher
     * @date 2019/8/14 16:03
     */
    boolean needCertificate() default false;

}
