package com.vteam.vtarm.utils;

import com.vteam.vtarm.CommonConstants;
import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Http请求工具类 .<br>
 *
 * @return
 * @date 2018/8/6 11:21
 */
public final class HttpRequestUtils {

    private static final String HEADER = "x-forwarded-for";

    private HttpRequestUtils() {
    }

    /**
     * 获取客户端真实IP .
     *
     * @param request 请求对象
     * @return java.lang.String 客户端真实IP
     * @author andy.sher
     * @date 2018/9/19 18:46
     */
    public static String getRemoteIP(HttpServletRequest request) {
        if (null == request.getHeader(HEADER)) {
            return request.getRemoteAddr();
        }
        String ip = request.getHeader(HEADER);
        String realIp = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(ip)) {
            if (ip.contains(CommonConstants.Symbol.COMMA)) {
                realIp = ip.split(CommonConstants.Symbol.COMMA)[0];
            } else {
                realIp = ip;
            }
        }
        return realIp;
    }

}
