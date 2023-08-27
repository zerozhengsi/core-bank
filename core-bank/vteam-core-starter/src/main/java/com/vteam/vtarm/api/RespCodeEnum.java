package com.vteam.vtarm.api;

import org.apache.commons.lang3.StringUtils;

/**
 * 响应代码 .<br>
 *
 * @author andy.sher
 * @date 2018/7/10 17:19
 */
public enum RespCodeEnum {

    /**
     * 请求成功
     */
    SUCCESS(1, StringUtils.EMPTY),

    /**
     * 请求失败
     */
    FAILD(0, "请求失败。"),

    /**
     * 请求中含有非法参数
     */
    ILLEGAL_PARAMETER(2, "检测到非法参数，请刷新页面后重试。"),

    /**
     * 认证失败
     */
    UNAUTHENTICATED(400, "认证失败。"),

    /**
     * 授权失败
     */
    UNAUTHORIZED(401, "授权失败。"),

    /**
     * TOKEN过期
     */
    TOKEN_EXPIRED(402, "登录已过期，请重新登录！"),

    /**
     * TOKEN无效
     */
    TOKEN_INVALID(403, "Token无效。"),

    /**
     * 数据未找到
     */
    NOT_FOUND(404, "数据未找到。"),

    /**
     * 用户尚未绑定微信
     */
    UNBIND_WECHAT_OPENID(405, "用户尚未绑定微信。"),

    /**
     * 拒绝处理请求
     */
    REQUEST_REFUSE(406, "拒绝处理请求。"),

    /**
     * 单点登录模式认证失败
     */
    UNAUTHENTICATED_SSO(407, "认证失败。");

    RespCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
