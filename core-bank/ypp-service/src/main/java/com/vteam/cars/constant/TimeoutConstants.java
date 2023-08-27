package com.vteam.cars.constant;

import lombok.Getter;

/**
 * 超时时间常量类 .<br>
 *
 * @author andy.sher
 * @date 2018/9/19 8:58
 */
@Getter
public class TimeoutConstants {

    /**
     * API缓存过期时间最小
     */
    public static final Integer API_CACHE_MIN_EXPIRE = 60;

    /**
     * API缓存过期时间最大
     */
    public static final Integer API_CACHE_MAX_EXPIRE = 600;

    /**
     * 验证码有效时间
     */
    public static final Integer VERIFICATION_CODE_TIMEOUT = 300;

    /**
     * HTTP 请求超时时间
     */
    public static final Integer HTTP_CONNECT_TIMEOUT = 6000;

    /**
     * 交易动态超时时间
     */
    public static final Integer TX_DYNAMIC_TIMEOUT = 86400;

    /**
     * Shibor超时时间
     */
    public static final Integer SHIBOR_TIMEOUT = 86400;

    /**
     * Libor超时时间
     */
    public static final Integer LIBOR_TIMEOUT = 86400;

    /**
     * 微信Token缓存时间
     */
    public static final Integer WX_TOKEN_TIMEOUT = 3600;

    /**
     * 系统缓存超时时间
     */
    public static final Integer DEFAULT_TIMEOUT = 86400;

    /**
     * Token超时时间
     */
    public static final Integer TOKEN_TIMEOUT = 3600;

    /**
     * 图形验证码超时时间
     */
    public static final Integer IMAGE_CODE_TIMEOUT = 300;

    /**
     * 邮箱验证码超时时间
     */
    public static final Integer EMAIL_CODE_TIMEOUT = 600;

    /**
     * 手机验证码超时时间
     */
    public static final Integer MOBILE_PHONE_CODE_TIMEOUT = 600;


}
