package com.vteam.vtarm.security;

import com.vteam.vtarm.CommonConstants;
import com.vteam.vtarm.filter.Filter;
import com.vteam.vtarm.status.GlobalStatus;
import com.vteam.vtarm.utils.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.PatternMatchUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

/**
 * 匿名认证过滤器 .<br>
 *
 * @author andy.sher
 * @date 2020/4/22 13:48
 */
@Slf4j
public class DefaultAnonymousAuthFilter implements Filter {

    private final SecurityProperties securityProperties;

    @Value("${server.servlet.context-path:}")
    private String servletContextPath;

    public DefaultAnonymousAuthFilter(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public boolean active() {
        boolean isAnonResource = isAnonResource(GlobalStatus.getRequest().getRequestURI());
        boolean active = Boolean.FALSE;
        if (isAnonResource) {
            log.debug("匿名认证校验已激活。");
            active = Boolean.TRUE;
        }
        return active;
    }

    @Override
    public void handle() throws RuntimeException {
        // 获取会话超时配置信息，若未设置，则默认30分钟
        SecurityProperties securityProperties = SpringContextUtils.getBean(SecurityProperties.class);
        Integer timeToLive = securityProperties.getTimeToLive();
        if (null == timeToLive) {
            timeToLive = AuthenticationInfo.DEFAULT_EXPIRE_MINUTES;
        }

        // 设置匿名用户认证信息
        GlobalStatus.setAnonymous(Boolean.TRUE);
        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        authenticationInfo.setAccessToken(UUID.randomUUID().toString());
        authenticationInfo.setSessionId(GlobalStatus.getRequest().getRequestedSessionId());
        authenticationInfo.setUserAgent(GlobalStatus.getRequest().getHeader(HttpHeaders.USER_AGENT));
        authenticationInfo.setExpiresIn(LocalDateTime.now().plusMinutes(timeToLive).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        GlobalStatus.setAuthenticationInfo(authenticationInfo);
        log.debug("匿名认证校验通过。");
    }

    @Override
    public int order() {
        return -1;
    }

    @Override
    public int type() {
        return PRE;
    }

    /**
     * 校验访问的url是否为匿名资源 .
     *
     * @param url 请求url
     * @return boolean 是否为匿名资源
     * @author andy.sher
     * @date 2019/4/22 13:33
     */
    private boolean isAnonResource(String url) {
        boolean isAnon = Boolean.FALSE;
        String anonUrls = securityProperties.getIgnoreUrls();
        // 是否是匿名资源
        if (StringUtils.isNotBlank(anonUrls)) {
            String[] anonUrlsArray = anonUrls.split(CommonConstants.Symbol.COMMA);
            String contextPath = StringUtils.isNotBlank(servletContextPath) ? servletContextPath : StringUtils.EMPTY;
            for (int i = 0, len = anonUrlsArray.length; i < len; i++) {
                if (PatternMatchUtils.simpleMatch(contextPath + anonUrlsArray[i], url)) {
                    isAnon = Boolean.TRUE;
                    break;
                }
            }
        }
        return isAnon;
    }

}
