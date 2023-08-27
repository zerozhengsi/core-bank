package com.vteam.vtarm.security;

import com.vteam.vtarm.status.GlobalStatus;
import org.springframework.util.PatternMatchUtils;

/**
 * 登录请求后置处理器 .<br>
 *
 * @author andy.sher
 * @date 2019/11/21 16:18
 */
public class DefaultLogoutFilter extends LogoutFilter {

    private final TokenContainer tokenContainer;

    public DefaultLogoutFilter(TokenContainer tokenContainer) {
        this.tokenContainer = tokenContainer;
    }

    @Override
    public boolean active() {
        return PatternMatchUtils.simpleMatch(LOGOUT_URL, GlobalStatus.getRequest().getRequestURI());
    }

    @Override
    public int order() {
        return ORDER_MAX;
    }

    @Override
    public void handle() throws RuntimeException {
        tokenContainer.delete(GlobalStatus.getAuthenticationInfo().getAccessToken());
    }

    @Override
    public int type() {
        return AFTER;
    }
}
