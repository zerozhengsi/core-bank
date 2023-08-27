package com.vteam.vtarm.security;

import java.lang.annotation.*;

/**
 * 刷新授权信息 .<br>
 *
 * @author andy.sher
 * @date 2019/9/25 15:08
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RefreshTokenInfo {

    /**
     * 系统类型（前端用户或者后端用户） .
     *
     * @param
     * @return java.lang.String
     * @author andy.sher
     * @date 2019/9/25 15:11
     */
    String systemType();

}
