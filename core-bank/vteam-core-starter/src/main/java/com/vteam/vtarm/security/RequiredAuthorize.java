package com.vteam.vtarm.security;

import java.lang.annotation.*;

/**
 * 加上此注解的 controller 需要授权才能访问 .<br>
 *
 * @author andy.sher
 * @date 2019/4/22 13:27
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiredAuthorize {

    /**
     * 需要的权限 .
     *
     * @param
     * @return java.lang.String[] 权限数组
     * @author andy.sher
     * @date 2019/4/22 13:53
     */
    String[] permissions() default {};

    /**
     * 需要的角色 .
     *
     * @param
     * @return java.lang.String[] 角色数组
     * @author andy.sher
     * @date 2019/4/22 13:53
     */
    String[] roles() default {};

    /**
     * 多个权限时，逻辑用and，默认为or .
     *
     * @param
     * @return boolean
     * @author andy.sher
     * @date 2019/4/22 13:53
     */
    boolean andPermission() default false;

    /**
     * 多个角色时，逻辑用and，默认为or .
     *
     * @param
     * @return boolean
     * @author andy.sher
     * @date 2019/4/22 13:54
     */
    boolean andRole() default false;

    /**
     * 是否需要登录 .
     *
     * @param
     * @return boolean 是否需要登录
     * @author andy.sher
     * @date 2019/4/22 13:54
     */
    boolean requiredLogin() default true;

}
