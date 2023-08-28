package com.vteam.cars.constant;

/**
 * 系统配置常量类 .<br>
 *
 * @author andy.sher
 * @date 2018/8/24 14:24
 */
public class SmeConfigConstants {

    /**
     * 是否开启多语言[1:是,0:否(default)]
     */
    public static final String P_SYSTEM_LANGUAGE_OPEN = "P_SYSTEM_LANGUAGE_OPEN";

    /**
     * 系统所属平台
     */
    public static final String W_SYSTEM_PLATFORM_NAME = "W_SYSTEM_PLATFORM_NAME";


    /**
     * 系统配置默认语言
     */
    public static final String W_SYSTEM_LANGUAGE = "W_SYSTEM_LANGUAGE";

    /**
     * 后台服务访问地址
     */
    public static final String W_LOCAL_URL = "W_LOCAL_URL";

    /**
     * 是否开启短信
     */
    public static final String SMS_SWITCH = "SMS_SWITCH";

    /**
     * 短信发送地址
     */
    public static final String W_SEND_SMS_URL = "W_SEND_SMS_URL";

    /**
     * 是否允许用户重复登录 Y：允许；N：不允许
     */
    public static final String REPEATED_LOGIN_FLAG = "REPEATED_LOGIN_FLAG";

    /**
     * 使用本地文件仓库
     */
    public static final String FILE_ACTIVE_LOCAL = "local";

    /**
     * 验证码类型[1=短信/0=图形]
     */
    public static final String VERIFICATION_CODE_TYPE = "VERIFICATION_CODE_TYPE";

    /**
     * 报价币种
     */
    public static final String CCYID = "CCYID";

    /**
     * 默认品牌
     */
    public static final String W_FIXED_BRAND_CODE = "W_FIXED_BRAND_CODE";

    /**
     * 品牌流水号
     */
    public static final String BRAND_REFCODE = "BRAND_REFCODE";

    /**
     * 品牌名称
     */
    public static final String BRAND_NAME = "BRAND_NAME";

    /**
     * 品牌电话
     */
    public static final String BRAND_HELP_HOTLINE = "BRAND_HELP_HOTLINE";

    /**
     * 品牌类型
     */
    public static final String BRAND_STYLE = "BRAND_STYLE";

    /**
     * 邮箱服务器运营单位
     */
    public static final String MAILBOX_OPERATOR = "MAILBOX_OPERATOR";

    /****************************** 运行环境-微信参数设置 ******************************/
    /**
     * 开发者ID(AppID)
     */
    public static final String P_WX_APP_ID = "P_WX_APP_ID";

    /**
     * 开发者密码(AppSecret)
     */
    public static final String P_WX_APP_SECRET = "P_WX_APP_SECRET";

    /**
     * 开发者配置令牌(Token)
     */
    public static final String P_WX_TOKEN = "P_WX_TOKEN";

    /**
     * 是否启用微信公众号
     */
    public static final String P_WX_OPEN = "P_WX_OPEN";


}
