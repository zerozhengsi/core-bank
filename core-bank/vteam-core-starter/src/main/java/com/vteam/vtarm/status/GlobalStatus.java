package com.vteam.vtarm.status;

import com.vteam.vtarm.security.AuthenticationInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 全局状态管理器 .<br>
 *
 * @author andy.sher
 * @date 2019/11/19 16:53
 */
public class GlobalStatus {

    /**
     * 认证状态
     */
    public static final String AUTH_STATUS = "auth_status";

    /**
     * 是否为匿名用户
     */
    private static ThreadLocal<Boolean> anonymous = new ThreadLocal<>();

    /**
     * 拒绝处理请求
     */
    private static ThreadLocal<Boolean> requestRefuse = new ThreadLocal<>();

    /**
     * IP地址
     */
    private static ThreadLocal<String> ipAddress = new ThreadLocal<>();

    /**
     * 请求对象
     */
    private static ThreadLocal<HttpServletRequest> request = new ThreadLocal<>();

    /**
     * 响应对象
     */
    private static ThreadLocal<HttpServletResponse> response = new ThreadLocal<>();

    /**
     * 认证信息
     */
    private static ThreadLocal<AuthenticationInfo> authenticationInfo = new ThreadLocal<>();

    /**
     * 业务执行开始时间
     */
    private static ThreadLocal<Long> businessStartTime = new ThreadLocal<>();

    /**
     * 业务执行结束时间
     */
    private static ThreadLocal<Long> businessEndTime = new ThreadLocal<>();

    /**
     * 请求开始时间
     */
    private static ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 请求结束时间
     */
    private static ThreadLocal<Long> endTime = new ThreadLocal<>();

    /**
     * 认证状态
     */
    private static ThreadLocal<Boolean> authSuccess = new ThreadLocal<>();

    /**
     * 认证过期状态
     */
    private static ThreadLocal<Boolean> authExpired = new ThreadLocal<>();

    /**
     * 设置认证是否通过 .
     *
     * @param authSuccess 认证是否通过
     * @return void
     * @author andy.sher
     * @date 2019/11/19 16:59
     */
    public static void setAuthSuccess(Boolean authSuccess) {
        GlobalStatus.authSuccess.set(authSuccess);
    }

    /**
     * 获取认证是否通过 .
     *
     * @param
     * @return java.lang.Boolean 认证是否通过
     * @author andy.sher
     * @date 2019/11/19 16:59
     */
    public static Boolean getAuthSuccess() {
        return GlobalStatus.authSuccess.get();
    }

    /**
     * 设置认证是否过期 .
     *
     * @param authExpired 认证是否过期
     * @return void
     * @author andy.sher
     * @date 2019/11/19 17:06
     */
    public static void setAuthExpired(Boolean authExpired) {
        GlobalStatus.authExpired.set(authExpired);
    }

    /**
     * 获取认证是否过期 .
     *
     * @param
     * @return java.lang.Boolean 认证是否过期
     * @author andy.sher
     * @date 2019/11/19 17:06
     */
    public static Boolean getAuthExpired() {
        return GlobalStatus.authExpired.get();
    }

    /**
     * 设置开始时间 .
     *
     * @param startTime 开始时间
     * @return void
     * @author andy.sher
     * @date 2019/11/19 16:16
     */
    public static void setStartTime(Long startTime) {
        GlobalStatus.startTime.set(startTime);
    }

    /**
     * 获取开始时间 .
     *
     * @param
     * @return java.lang.Long 开始时间
     * @author andy.sher
     * @date 2019/11/19 16:16
     */
    public static Long getStartTime() {
        return GlobalStatus.startTime.get();
    }

    /**
     * 设置结束时间 .
     *
     * @param endTime 结束时间
     * @return void
     * @author andy.sher
     * @date 2019/11/19 16:16
     */
    public static void setEndTime(Long endTime) {
        GlobalStatus.endTime.set(endTime);
    }

    /**
     * 获取结束时间 .
     *
     * @param
     * @return java.lang.Long 结束时间
     * @author andy.sher
     * @date 2019/11/19 16:17
     */
    public static Long getEndTime() {
        return GlobalStatus.endTime.get();
    }

    /**
     * 设置业务结束时间 .
     *
     * @param businessEndTime 业务结束时间
     * @return void
     * @author andy.sher
     * @date 2019/11/19 16:15
     */
    public static void setBusinessEndTime(Long businessEndTime) {
        GlobalStatus.businessEndTime.set(businessEndTime);
    }

    /**
     * 获取业务结束时间 .
     *
     * @param
     * @return java.lang.Long 业务结束时间
     * @author andy.sher
     * @date 2019/11/19 16:15
     */
    public static Long getBusinessEndTime() {
        if (null == GlobalStatus.businessEndTime.get()) {
            return System.currentTimeMillis();
        } else {
            return GlobalStatus.businessEndTime.get();
        }
    }

    /**
     * 设置业务开始时间 .
     *
     * @param businessStartTime 业务开始时间
     * @return void
     * @author andy.sher
     * @date 2019/11/19 16:15
     */
    public static void setBusinessStartTime(Long businessStartTime) {
        GlobalStatus.businessStartTime.set(businessStartTime);
    }

    /**
     * 获取业务开始时间 .
     *
     * @param
     * @return java.lang.Long 业务开始时间
     * @author andy.sher
     * @date 2019/11/19 16:16
     */
    public static Long getBusinessStartTime() {
        if (null == GlobalStatus.businessStartTime.get()) {
            return System.currentTimeMillis();
        } else {
            return GlobalStatus.businessStartTime.get();
        }
    }

    /**
     * 获取认证信息 .
     *
     * @param
     * @return com.vteam.vtarm.security.AuthenticationInfo 认证信息
     * @author andy.sher
     * @date 2019/11/21 14:20
     */
    public static AuthenticationInfo getAuthenticationInfo() {
        return GlobalStatus.authenticationInfo.get();
    }

    /**
     * 设置认证信息 .
     *
     * @param authenticationInfo 认证信息
     * @return void
     * @author andy.sher
     * @date 2019/11/21 14:20
     */
    public static void setAuthenticationInfo(AuthenticationInfo authenticationInfo) {
        GlobalStatus.authenticationInfo.set(authenticationInfo);
    }

    /**
     * 设置http请求对象 .
     *
     * @param request http请求对象
     * @return void
     * @author andy.sher
     * @date 2019/11/21 16:08
     */
    public static void setRequest(HttpServletRequest request) {
        GlobalStatus.request.set(request);
    }

    /**
     * 获取http请求对象 .
     *
     * @param
     * @return jakarta.servlet.http.HttpServletRequest http请求对象
     * @author andy.sher
     * @date 2019/11/21 16:08
     */
    public static HttpServletRequest getRequest() {
        return GlobalStatus.request.get();
    }

    /**
     * 设置http响应对象 .
     *
     * @param response http响应对象
     * @return void
     * @author andy.sher
     * @date 2019/11/21 16:08
     */
    public static void setResponse(HttpServletResponse response) {
        GlobalStatus.response.set(response);
    }

    /**
     * 获取http响应对象 .
     *
     * @param
     * @return jakarta.servlet.http.HttpServletResponse http响应对象
     * @author andy.sher
     * @date 2019/11/21 16:08
     */
    public static HttpServletResponse getResponse() {
        return GlobalStatus.response.get();
    }

    /**
     * 获取IP地址 .
     *
     * @param
     * @return java.lang.String IP地址
     * @author andy.sher
     * @date 2018/9/10 14:02
     */
    public static String getIpAddress() {
        return GlobalStatus.ipAddress.get();
    }

    /**
     * 设置IP地址 .
     *
     * @param ipAddress IP地址
     * @return void
     * @author andy.sher
     * @date 2018/12/29 9:10
     */
    public static void setIpAddress(String ipAddress) {
        GlobalStatus.ipAddress.set(ipAddress);
    }

    /**
     * 获取是否拒绝请求 .
     *
     * @param
     * @return java.lang.Boolean 是否拒绝请求
     * @author andy.sher
     * @date 2019/11/28 15:18
     */
    public static Boolean getRequestRefuse() {
        return requestRefuse.get();
    }

    /**
     * 设置是否拒绝请求 .
     *
     * @param requestRefuse 是否拒绝请求
     * @return void
     * @author andy.sher
     * @date 2019/11/28 15:18
     */
    public static void setRequestRefuse(Boolean requestRefuse) {
        GlobalStatus.requestRefuse.set(requestRefuse);
    }

    /**
     * 设置是否为匿名用户 .
     *
     * @param anonymous 是否为匿名用户
     * @return void
     * @author andy.sher
     * @date 2020/4/26 17:10
     */
    public static void setAnonymous(Boolean anonymous) {
        GlobalStatus.anonymous.set(anonymous);
    }

    /**
     * 获取是否为匿名用户 .
     *
     * @param
     * @return java.lang.Boolean 是否为匿名用户
     * @author andy.sher
     * @date 2020/4/26 17:11
     */
    public static Boolean isAnonymous() {
        if (null == GlobalStatus.anonymous.get()) {
            GlobalStatus.anonymous.set(Boolean.FALSE);
        }
        return GlobalStatus.anonymous.get();
    }

    /**
     * 销毁参数库 .
     *
     * @param
     * @return void
     * @author andy.sher
     * @date 2019/11/21 14:13
     */
    public static void destroy() {
        startTime.remove();
        endTime.remove();
        businessStartTime.remove();
        businessEndTime.remove();
        authSuccess.remove();
        authExpired.remove();
        authenticationInfo.remove();
        request.remove();
        response.remove();
        ipAddress.remove();
        requestRefuse.remove();
        anonymous.remove();
    }

}
