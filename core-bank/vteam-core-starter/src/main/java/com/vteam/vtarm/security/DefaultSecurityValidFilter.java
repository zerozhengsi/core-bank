package com.vteam.vtarm.security;

import com.vteam.vtarm.filter.Filter;
import com.vteam.vtarm.status.GlobalStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;

/**
 * 校验是否认证过滤器 .<br>
 *
 * @author andy.sher
 * @date 2020/4/22 11:03
 */
@Slf4j
public class DefaultSecurityValidFilter implements Filter {

    private final SecurityProperties securityProperties;

    public DefaultSecurityValidFilter(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public boolean active() {
        boolean notAnonymous = !GlobalStatus.isAnonymous();
        boolean active = Boolean.FALSE;
        if (notAnonymous && highLevel()) {
            log.debug("高级别安控校验已激活。");
            active = Boolean.TRUE;
        }
        return active;
    }

    @Override
    public int order() {
        return -1;
    }

    @Override
    public void handle() throws RuntimeException {
        if (null == GlobalStatus.getAuthenticationInfo()) {
            throw new UnauthenticatedException();
        }
        // 判断是否为同一个session，查看session中的签名值和认证信息中的签名值是否一致
        success(baseValidate(), "客户端异常。");
    }

    @Override
    public int type() {
        return PRE;
    }

    /**
     * 获取是否为高安全等级 .
     *
     * @param
     * @return boolean 是否为高安全等级
     * @author andy.sher
     * @date 2019/5/31 17:39
     */
    private boolean highLevel() {
        return SecurityProperties.LEVEL.HIGH.equals(securityProperties.getLevel());
    }

    /**
     * 基础校验 .
     *
     * @return boolean 是否通过校验
     * @author andy.sher
     * @date 2019/5/31 16:33
     */
    private boolean baseValidate() {
        return GlobalStatus.getRequest().getHeader(HttpHeaders.USER_AGENT).equalsIgnoreCase(GlobalStatus.getAuthenticationInfo().getUserAgent());
    }

    /**
     * 认证成功断言 .
     *
     * @param value
     * @return void
     * @author andy.sher
     * @date 2019/11/19 13:39
     */
    private void success(boolean value, String message) throws UnauthenticatedException {
        if (!value) {
            throw new UnauthenticatedException(message);
        }
    }

}
