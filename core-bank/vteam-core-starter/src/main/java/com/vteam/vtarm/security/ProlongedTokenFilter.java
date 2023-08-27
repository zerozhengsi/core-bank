package com.vteam.vtarm.security;

import com.alibaba.fastjson.JSONObject;
import com.vteam.vtarm.filter.Filter;
import com.vteam.vtarm.status.GlobalStatus;
import com.vteam.vtarm.utils.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.task.TaskExecutor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * token 续费过滤器 .<br>
 *
 * @author andy.sher
 * @date 2020/4/22 12:04
 */
@Slf4j
public class ProlongedTokenFilter implements Filter {

    private final TokenContainer tokenContainer;

    private final TaskExecutor taskExecutor;

    private String CRITICAL_TIME = "critical_time:%s";

    private DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ProlongedTokenFilter(TokenContainer tokenContainer, TaskExecutor taskExecutor) {
        this.tokenContainer = tokenContainer;
        this.taskExecutor = taskExecutor;
    }

    @Override
    public boolean active() {
        boolean active = Boolean.FALSE;
        if (!GlobalStatus.isAnonymous()) {
            String key = String.format(CRITICAL_TIME, GlobalStatus.getAuthenticationInfo().getAccessToken());
            String exp = tokenContainer.get(key);
            if (StringUtils.isBlank(exp)) {
                log.debug("Token 延时已激活。");
                active = Boolean.TRUE;
                // 900秒进行一次 Token 延时
                tokenContainer.set(key, DTF.format(LocalDateTime.now()), 900);
            }
        }
        return active;
    }

    @Override
    public int order() {
        return ORDER_MIN;
    }

    @Override
    public void handle() throws RuntimeException {

        final AuthenticationInfo authenticationInfo = GlobalStatus.getAuthenticationInfo();
        // 认证信息有效期延时
        taskExecutor.execute(() -> {
            // 获取会话超时配置信息，若未设置，则默认30分钟
            SecurityProperties securityProperties = SpringContextUtils.getBean(SecurityProperties.class);
            Integer timeToLive = securityProperties.getTimeToLive();
            if (null == timeToLive) {
                timeToLive = AuthenticationInfo.DEFAULT_EXPIRE_MINUTES;
            }

            GlobalStatus.setAuthenticationInfo(authenticationInfo);
            // 计算新的过期时间
            LocalDateTime exTime = LocalDateTime.now().plusSeconds(TimeUnit.MINUTES.toSeconds(timeToLive));
            GlobalStatus.getAuthenticationInfo().setExpiresIn(exTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

            // 缓存新的token信息
            tokenContainer.set(GlobalStatus.getAuthenticationInfo().getAccessToken(), JSONObject.toJSONString(GlobalStatus.getAuthenticationInfo()), TimeUnit.MINUTES.toSeconds(timeToLive));
        });
    }

    @Override
    public int type() {
        return AFTER;
    }
}
