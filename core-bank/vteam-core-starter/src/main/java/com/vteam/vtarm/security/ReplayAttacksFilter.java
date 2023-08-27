package com.vteam.vtarm.security;

import com.vteam.vtarm.cache.StringValueContainer;
import com.vteam.vtarm.filter.CorsFilter;
import com.vteam.vtarm.filter.Filter;
import com.vteam.vtarm.status.GlobalStatus;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.PatternMatchUtils;

/**
 * 防重放攻击过滤器 .<br>
 *
 * @author andy.sher
 * @date 2019/11/28 14:41
 */
@Slf4j
public class ReplayAttacksFilter implements Filter {

    private final StringValueContainer stringValueContainer;

    private final SecurityProperties securityProperties;

    @Value("${server.servlet.context-path:}")
    private String servletContextPath;

    public ReplayAttacksFilter(StringValueContainer stringValueContainer, SecurityProperties securityProperties) {
        this.stringValueContainer = stringValueContainer;
        this.securityProperties = securityProperties;
    }

    @Override
    public boolean active() {
        return true;
    }

    @Override
    public int order() {
        return -1;
    }

    @Override
    public void handle() throws RuntimeException {
        final HttpServletRequest request = GlobalStatus.getRequest();
        String key = String.format("%s-%s-%s", request.getRequestedSessionId(), request.getRequestURI(), request.getParameter("t"));
        boolean process = stringValueContainer.setIfAbsent(key, key, 60);
        boolean allowMethod = CorsFilter.ALLOW_METHOEDS.contains(request.getMethod()); // 非允许请求类型，不做控制
        if (!process && !isIgnore() && allowMethod) {
            log.warn("来自客户端IP: {} 的可疑请求。访问的资源: {}", GlobalStatus.getIpAddress(), request.getRequestURI());
            throw new ReplayAttacksException();
        } else {
            GlobalStatus.setRequestRefuse(false);
        }
    }

    /**
     * 是否是允许重放的资源 .
     *
     * @param
     * @return void
     * @author andy.sher
     * @date 2019/12/5 15:18
     */
    private boolean isIgnore() {
        boolean ignore = false;
        String[] ignoreUrls = securityProperties.getAllowReplayUrls();
        if (ArrayUtils.isNotEmpty(ignoreUrls)) {
            String contextPath = StringUtils.isNotBlank(servletContextPath) ? servletContextPath : StringUtils.EMPTY;
            for (int i = 0, len = ignoreUrls.length; i < len; i++) {
                if (PatternMatchUtils.simpleMatch(contextPath + ignoreUrls[i], GlobalStatus.getRequest().getRequestURI())) {
                    ignore = true;
                    break;
                }
            }
        }
        return ignore;
    }

    @Override
    public int type() {
        return PRE;
    }
}
