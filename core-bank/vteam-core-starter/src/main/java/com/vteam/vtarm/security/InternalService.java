package com.vteam.vtarm.security;

import java.lang.annotation.*;

/**
 * 内部服务接口 .<br>
 *
 * @author andy.sher
 * @date 2019/12/23 10:29
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InternalService {

    /**
     * 允许访问的网段 .
     *
     * @param
     * @return java.lang.String 允许放行的网段，支持通配符
     * @author andy.sher
     * @date 2019/12/23 10:30
     */
    String segment() default "127.0.0.1";

}
