package com.vteam.vtarm.security;

import com.alibaba.fastjson.JSONObject;
import com.vteam.vtarm.filter.Filter;
import com.vteam.vtarm.status.GlobalStatus;
import com.vteam.vtarm.utils.SpringContextUtils;

import org.springframework.util.PatternMatchUtils;

import java.util.concurrent.TimeUnit;

/**
 * 登录请求后置处理器 .<br>
 *
 * @author andy.sher
 * @date 2019/11/21 16:18
 */
public class LoginFilter implements Filter {

    public static final String LOGIN_URL = "/**/login**";

    private final TokenContainer tokenContainer;

    public LoginFilter(TokenContainer tokenContainer) {
        this.tokenContainer = tokenContainer;
    }

    @Override
    public boolean active() {
        return PatternMatchUtils.simpleMatch(LOGIN_URL, GlobalStatus.getRequest().getRequestURI());
    }

    @Override
    public int order() {
        return ORDER_MAX;
    }

    @Override
    public void handle() throws RuntimeException {
        // 获取会话超时配置信息，若未设置，则默认30分钟
        SecurityProperties securityProperties = SpringContextUtils.getBean(SecurityProperties.class);
        Integer timeToLive = securityProperties.getTimeToLive();
        if (null == timeToLive) {
            timeToLive = AuthenticationInfo.DEFAULT_EXPIRE_MINUTES;
        }

        // 缓存登录会话信息
        final AuthenticationInfo authenticationInfo = GlobalStatus.getAuthenticationInfo();
        if (null != authenticationInfo) {
            tokenContainer.set(authenticationInfo.getAccessToken(), JSONObject.toJSONString(authenticationInfo), TimeUnit.MINUTES.toSeconds(timeToLive));
        }
    }

    @Override
    public int type() {
        return AFTER;
    }
}
