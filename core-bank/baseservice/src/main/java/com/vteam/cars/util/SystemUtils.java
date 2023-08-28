package com.vteam.cars.util;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 系统信息工具类
 *
 * @author Tianci.li
 * @since 2022/11/23 11:35
 */
public class SystemUtils {

    private static final String UNKNOWN = "unknown";

    private static final String BROWSER_CHROME = "Chrome";

    private static final String BROWSER_FIREFOX = "Firefox";

    private static final String BROWSER_CHROME_MOBILE = "Chrome Mobile";

    private static final String OS_ANDROID = "Android";

    private SystemUtils() {
    }

    /**
     * 获取IP地址
     *
     * @param request HttpServletRequest
     * @return String
     * @author Tianci.li
     * @since 2022/11/15 14:58
     */
    public static String getIpAddress(HttpServletRequest request) {
        String xIp = request.getHeader("X-Real-IP");
        String xFor = request.getHeader("X-Forwarded-For");

        if (StringUtils.isNotBlank(xFor) && !UNKNOWN.equalsIgnoreCase(xFor)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = xFor.indexOf(",");
            if (index != -1) {
                return xFor.substring(0, index);
            } else {
                return xFor;
            }
        }
        xFor = xIp;
        if (StringUtils.isNotBlank(xFor) && !UNKNOWN.equalsIgnoreCase(xFor)) {
            return xFor;
        }
        if (StringUtils.isBlank(xFor) || UNKNOWN.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xFor) || UNKNOWN.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xFor) || UNKNOWN.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(xFor) || UNKNOWN.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(xFor) || UNKNOWN.equalsIgnoreCase(xFor)) {
            xFor = request.getRemoteAddr();
        }
        return xFor;
    }

    /**
     * 获取浏览器名称
     *
     * @param request HttpServletRequest
     * @return String
     * @author Tianci.li
     * @since 2022/11/15 14:59
     */
    public static String getBrowserName(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        String name = browser.getName();
        if (name.startsWith(BROWSER_FIREFOX)) {
            name = BROWSER_FIREFOX;
        }
        if (name.startsWith(BROWSER_CHROME) && !BROWSER_CHROME_MOBILE.equals(name)){
            name = BROWSER_CHROME;
        }
        return name;
    }

    /**
     * 获取操作系统名称
     *
     * @param request HttpServletRequest
     * @since 2022/11/15 15:01
     * @author Tianci.li
     * @return String
     */
    public static String getOperatingSystemName(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        OperatingSystem os = userAgent.getOperatingSystem();
        return os.getName().startsWith(OS_ANDROID) ? OS_ANDROID : os.getName();
    }

}
