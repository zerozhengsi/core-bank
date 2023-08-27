package com.vteam.vtarm.security;

import com.alibaba.fastjson.JSONObject;
import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 认证信息 .<br>
 *
 * @author andy.sher
 * @date 2019/11/21 14:15
 */
@Data
@ToString
public class AuthenticationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 认证状态默认有效期（30分钟，可无限延时）
     */
    public static final Integer DEFAULT_EXPIRE_MINUTES = 30;

    /**
     * token
     */
    private String accessToken;

    /**
     * 用户代理信息
     */
    private String userAgent;

    /**
     * 过期时间
     */
    private long expiresIn;

    /**
     * sessionId
     */
    private String sessionId;

    /**
     * 角色
     */
    private Set<String> roles;

    /**
     * 权限
     */
    private Set<String> permissions;

    /**
     * 附带数据
     */
    private String additional;

    /**
     * 设置附带数据 .
     *
     * @param additional 附带数据
     * @return void
     * @author andy.sher
     * @date 2019/11/25 14:32
     */
    public void setAdditional(String additional, final AuthenticationInfo authenticationInfo) {
        // 获取会话超时配置信息，若未设置，则默认30分钟
        SecurityProperties securityProperties = SpringContextUtils.getBean(SecurityProperties.class);
        Integer timeToLive = securityProperties.getTimeToLive();
        if (null == timeToLive) {
            timeToLive = AuthenticationInfo.DEFAULT_EXPIRE_MINUTES;
        }

        this.additional = additional;
        TokenContainer tokenContainer = SpringContextUtils.getBean(TokenContainer.class);
        tokenContainer.set(authenticationInfo.getAccessToken(), JSONObject.toJSONString(authenticationInfo), TimeUnit.MINUTES.toSeconds(timeToLive));
    }
}
