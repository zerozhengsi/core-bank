package com.vteam.vtarm.security;

import com.vteam.vtarm.filter.Filter;

/**
 * 登出请求后置处理器 .<br>
 *
 * @author andy.sher
 * @date 2019/11/21 16:18
 */
public abstract class LogoutFilter implements Filter {

    protected static final String LOGOUT_URL = "/**/logout**";

    @Override
    public int order() {
        return ORDER_MIN;
    }

    @Override
    public int type() {
        return AFTER;
    }
}
