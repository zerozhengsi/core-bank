package com.vteam.cars.constant;

/**
 * 微信常量类 .
 *
 * @author fu.tong
 * @date 2019/6/28 11:18
 */
public final class WeiXinConstants {

    private WeiXinConstants() {

    }

    /**
     * 微信API返回参数ERRCODE
     */
    public static final String ERRCODE = "errcode";

    /**
     * 微信API返回参数ERRMSG
     */
    public static final String ERRMSG = "errmsg";

    /**
     * 成功
     */
    public static final int ERRORCODE_SUCCESS = 0;

    /**
     * 系统繁忙，此时请开发者稍候再试
     */
    public static final int ERRORCODE_SYSTEM_ERROR = -1;

    /**
     * js_code无效
     */
    public static final int ERRORCODE_40029 = 40029;

    /**
     * 微信code
     */
    public static final String JS_CODE = "jsCode";

    /**
     * 微信小程序请求url
     */
    public static final String URL = "https://api.weixin.qq.com/cgi-bin/token";

    /**
     * 获取微信小程序二维码url
     */
    public static final String QR_CODE_URL = "https://api.weixin.qq.com/wxa/getwxacode?access_token=";

    /** 小程序(同富车服小程序) */
    public final class Xcx {

        /**
         * appid
         */
        public static final String CONST_APPID_KEY = "xcx_cars";

        /**
         * grant_type
         */
        public static final String CONST_GRANT_TYPE = "authorization_code";

        /**
         * grant_type 获取接口调用凭据getAccessToken使用
         */
        public static final String CONST_ACCESS_TOKEN_GRANT_TYPE = "client_credential";
    }


    /** 人脸识别小程序 */
    public final class QRCode {

        /**
         * appid
         */
        public static final String CONST_APPID_KEY = "xcx_fr";

        /**
         * grant_type
         */
        public static final String CONST_GRANT_TYPE = "client_credential";

    }

    /**
     * 微信平台开放API
     */
    public final class Api{

        /**
         * 小程序登录请求URL
         */
        public static final String API_URL_JSCODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session";

        /**
         * 小程序登录API_NAME
         */
        public static final String API_NAME_JSCODE2SESSION = "小程序登录";

        /**
         * 获取微信用户手机号请求URL
         */
        public static final String API_URL_GETUSERPHONENUMBER = "https://api.weixin.qq.com/wxa/business/getuserphonenumber";

        /**
         * 获取微信用户手机号API_NAME
         */
        public static final String API_NAME_GETUSERPHONENUMBER = "获取微信用户手机号";

        /**
         * 获取微信接口调用凭据请求URL
         */
        public static final String API_URL_GETACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token";

        /**
         * 获取微信接口调用凭据API_NAME
         */
        public static final String API_NAME_GETACCESSTOKEN = "获取微信接口调用凭据";
    }


}
