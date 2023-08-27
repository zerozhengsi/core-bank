package com.vteam.vtarm.security;

import com.vteam.vtarm.CommonConstants;
import com.vteam.vtarm.VtarmActuatorProperties;
import com.vteam.vtarm.filter.Filter;
import com.vteam.vtarm.status.GlobalStatus;
import com.vteam.vtarm.utils.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.util.Base64Utils;
import org.springframework.util.PatternMatchUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

/**
 * Basic认证过滤器，仅对Actuator开放 .<br>
 *
 * @author andy.sher
 * @date 2020/4/20 17:26
 */
@Slf4j
public class DefaultBasicAuthFilter implements Filter {

    public static final String AUTHORIZATION = "authorization";
    public static final String BASIC = "Basic";

    private final VtarmActuatorProperties vtarmActuatorProperties;

    public DefaultBasicAuthFilter(VtarmActuatorProperties vtarmActuatorProperties) {
        this.vtarmActuatorProperties = vtarmActuatorProperties;
    }

    @Override
    public boolean active() {
        boolean notAnonymous = null == GlobalStatus.getAuthenticationInfo() || !GlobalStatus.isAnonymous();

        boolean hasBasic = Boolean.FALSE;
        boolean active = Boolean.FALSE;
        boolean isActuatorRequest = PatternMatchUtils.simpleMatch("/actuator/**", GlobalStatus.getRequest().getRequestURI());
        if (notAnonymous && isActuatorRequest) {
            boolean hasHeader = StringUtils.isNotBlank(GlobalStatus.getRequest().getHeader(AUTHORIZATION));
            if (hasHeader) {
                hasBasic = GlobalStatus.getRequest().getHeader(AUTHORIZATION).contains(BASIC);
            }
            if (hasBasic) {
                log.debug("Basic 认证校验已激活。");
                active = Boolean.TRUE;
            }
        }

        return active;
    }

    @Override
    public void handle() throws RuntimeException {
        String authorization = GlobalStatus.getRequest().getHeader(AUTHORIZATION);
        String cut = authorization.replaceFirst(BASIC, StringUtils.EMPTY).trim();
        String[] usernamePassword = new String(Base64Utils.decodeFromString(cut), StandardCharsets.UTF_8).split(CommonConstants.Symbol.SEMICOLON);
        String username = usernamePassword[0];
        String password = usernamePassword[1];

        if (StringUtils.isBlank(vtarmActuatorProperties.getUsername()) || StringUtils.isBlank(vtarmActuatorProperties.getPassword())) {
            log.error("未配置Actuator安全密码。");
        } else {
            if (!vtarmActuatorProperties.getUsername().equals(username) || !vtarmActuatorProperties.getPassword().equals(password)) {
                throw new UnauthenticatedException();
            }
            GlobalStatus.setAuthSuccess(Boolean.TRUE);

            // 获取会话超时配置信息，若未设置，则默认30分钟
            SecurityProperties securityProperties = SpringContextUtils.getBean(SecurityProperties.class);
            Integer timeToLive = securityProperties.getTimeToLive();
            if (null == timeToLive) {
                timeToLive = AuthenticationInfo.DEFAULT_EXPIRE_MINUTES;
            }

            // 设置匿名用户认证信息
            GlobalStatus.setAnonymous(Boolean.FALSE);
            AuthenticationInfo authenticationInfo = new AuthenticationInfo();
            authenticationInfo.setAccessToken(UUID.randomUUID().toString());
            authenticationInfo.setSessionId(GlobalStatus.getRequest().getRequestedSessionId());
            authenticationInfo.setUserAgent(GlobalStatus.getRequest().getHeader(HttpHeaders.USER_AGENT));
            authenticationInfo.setExpiresIn(LocalDateTime.now().plusMinutes(timeToLive).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            GlobalStatus.setAuthenticationInfo(authenticationInfo);
            log.debug("Basic 认证通过。");
        }
    }

    @Override
    public int order() {
        return -1;
    }

    @Override
    public int type() {
        return PRE;
    }
}
