package com.vteam.vtarm.security;

import java.lang.annotation.*;

/**
 * 此注解用于声明在Controller 上面，被声明的Controller方法表示一个登出请求，将在响应之前注销用户认证信息 .<br>
 *
 * @author andy.sher
 * @date 2019/4/19 9:18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogoutRequest {
}
