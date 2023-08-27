package com.vteam.vtarm.security;

import com.alibaba.fastjson.JSONObject;
import com.vteam.vtarm.CommonConstants;
import com.vteam.vtarm.filter.Filter;
import com.vteam.vtarm.status.GlobalStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.PatternMatchUtils;

/**
 * Url Access Token认证过滤器 .<br>
 *
 * @author andy.sher
 * @date 2019/11/19 10:17
 */
@Slf4j
public class DefaultUrlAuthFilter implements Filter {

    /**
     * token
     */
    public static final String TOKEN = "access_token";

    private final TokenContainer tokenContainer;

    private final SecurityProperties securityProperties;

    @Value("${server.servlet.context-path:}")
    private String servletContextPath;

    public DefaultUrlAuthFilter(TokenContainer tokenContainer, SecurityProperties securityProperties) {
        this.tokenContainer = tokenContainer;
        this.securityProperties = securityProperties;
    }

    @Override
    public boolean active() {
        boolean active = Boolean.FALSE;
        final String queryString = GlobalStatus.getRequest().getQueryString();
        if (StringUtils.isNotBlank(queryString) && queryString.contains(TOKEN)) {
            log.debug("Url Token 认证校验已激活。");
            active = Boolean.TRUE;
        }
        return active;
    }

    @Override
    public void handle() throws RuntimeException {

        String accessToken = GlobalStatus.getRequest().getParameter(TOKEN);
        String tokenInfoStr = tokenContainer.get(accessToken);
        AuthenticationInfo authenticationInfo = JSONObject.parseObject(tokenInfoStr, AuthenticationInfo.class);
        if (null == authenticationInfo) {
            boolean isAnonResource = isAnonResource(GlobalStatus.getRequest().getRequestURI());
            // 可匿名访问资源不判断Token
            if (!isAnonResource) {
                GlobalStatus.setAuthExpired(Boolean.TRUE);
                throw new TokenExpiredException();
            }
        } else {
            // 存储认证信息
            GlobalStatus.setAuthenticationInfo(authenticationInfo);
            GlobalStatus.setAuthSuccess(Boolean.TRUE);
        }
        log.debug("Url Token 认证通过。");
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
