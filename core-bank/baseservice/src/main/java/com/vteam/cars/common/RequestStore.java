package com.vteam.cars.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.entity.vo.FlogApiMVo;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.plugin.weixin.configure.WeixinPropertiesUtils;
import com.vteam.cars.util.SmeAssert;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.security.AuthenticationInfo;
import com.vteam.vtarm.status.GlobalStatus;
import com.vteam.vtarm.utils.SpringContextUtils;

/**
 * 请求参数仓库 .<br>
 *
 * @author andy.sher
 * @date 2018/7/25 11:41
 */
public class RequestStore {

    /**
     * 品牌代号
     */
    private static ThreadLocal<String> brandCode = new ThreadLocal<>();

    /**
     * appID
     */
    private static ThreadLocal<String> appID = new ThreadLocal<>();

    /**
     * 分页下标
     */
    private static ThreadLocal<Integer> pageNum = new ThreadLocal<>();

    /**
     * 分页大小
     */
    private static ThreadLocal<Integer> pageSize = new ThreadLocal<>();

    /**
     * 分页字段和方式
     */
    private static ThreadLocal<JSONObject> sort = new ThreadLocal<>();

    /**
     * 令牌
     */
    private static ThreadLocal<String> token = new ThreadLocal<>();

    /**
     * 位置
     */
    private static ThreadLocal<String> location = new ThreadLocal<>();

    /**
     * 用户ID
     */
    private static ThreadLocal<String> userid = new ThreadLocal<>();

    /**
     * 城市代码
     */
    private static ThreadLocal<String> cityCode = new ThreadLocal<>();

    /**
     * 客户端类型
     */
    private static ThreadLocal<String> client = new ThreadLocal<>();

    /**
     * 分页信息
     */
    private static ThreadLocal<Page<Object>> page = new ThreadLocal<>();

    /**
     * 语言
     */
    private static ThreadLocal<String> language = new ThreadLocal<>();

    /**
     * 请求接口日志
     */
    private static ThreadLocal<FlogApiMVo> apiLog = new ThreadLocal<>();

    public static int getPageNum() {
        return RequestStore.pageNum.get();
    }

    public static void setPageNum(int pageNum) {
        RequestStore.pageNum.set(pageNum);
    }

    public static int getPageSize() {
        return RequestStore.pageSize.get();
    }

    public static void setPageSize(int pageSize) {
        RequestStore.pageSize.set(pageSize);
    }

    public static JSONObject getSort() {
        return RequestStore.sort.get();
    }

    public static void setSort(JSONObject sort) {
        RequestStore.sort.set(sort);
    }

    /**
     * 设置客户端类型 .
     *
     * @param client 客户端类型
     * @author andy.sher
     * @date 2018/12/28 15:59
     */
    public static void setClient(String client) {
        RequestStore.client.set(client);
    }

    /**
     * 获取客户端类型 .
     *
     * @return java.lang.String 客户端类型
     * @author andy.sher
     * @date 2018/12/28 16:00
     */
    public static String getClient() {
        return RequestStore.client.get();
    }

    /**
     * 获取用户id<br>.
     *
     * @author xia.hang
     * @date 2023/5/23 14:45

     * @return String
    */
    public static String getUserid() {
        SipaBurMVo sipaBurMVo;
        final AuthenticationInfo authenticationInfo = GlobalStatus.getAuthenticationInfo();
        if (GlobalStatus.isAnonymous() && (null == authenticationInfo || StringUtils.isBlank(authenticationInfo.getAdditional()))) {
            sipaBurMVo = new SipaBurMVo();
        } else {
            sipaBurMVo = JSON.parseObject(authenticationInfo.getAdditional(), SipaBurMVo.class);
        }
        return sipaBurMVo.getUserid();
    }

    /**
     * 获取当前登录用户信息 .
     *
     * @return com.vteam.cars.entity.vo.SipaBurMVo 当前登录用户
     * @author andy.sher
     * @date 2019/4/15 18:45
     */
    public static SipaBurMVo getLoginUser() {
        SipaBurMVo sipaBurMVo;
        final AuthenticationInfo authenticationInfo = GlobalStatus.getAuthenticationInfo();
        if (GlobalStatus.isAnonymous() && (null == authenticationInfo || StringUtils.isBlank(authenticationInfo.getAdditional()))) {
            sipaBurMVo = new SipaBurMVo();
        } else {
            sipaBurMVo = JSON.parseObject(authenticationInfo.getAdditional(), SipaBurMVo.class);
        }
        return sipaBurMVo;
    }

    /**
     * 设置分页信息 .
     *
     * @param page 分页对象
     * @author andy.sher
     * @date 2019/7/15 10:12
     */
    public static void setPage(Page<Object> page) {
        RequestStore.page.set(page);
    }

    /**
     * 获取分页对象 .
     *
     * @return com.github.pagehelper.Page<java.lang.Object> 分页对象
     * @author andy.sher
     * @date 2019/7/15 10:12
     */
    public static Page<Object> getPage() {
        return RequestStore.page.get();
    }

    /**
     * 设置客户端语言 .
     *
     * @param language 客户端语言
     * @author andy.sher
     * @date 2019/7/15 10:13
     */
    public static void setLanguage(String language) {
        RequestStore.language.set(language);
    }

    /**
     * 获取客户端语言 .
     *
     * @return java.lang.String 客户端语言
     * @author andy.sher
     * @date 2019/7/15 10:13
     */
    public static String getLanguage() {
        return RequestStore.language.get();
    }

    /**
     * 获取接口日志对象 .
     *
     * @return com.vteam.cars.entity.vo.FlogApiMVo 接口日志对象
     * @author andy.sher
     * @date 2019/9/29 13:44
     */
    public static FlogApiMVo getApiLog() {
        if (null == RequestStore.apiLog.get()) {
            RequestStore.apiLog.set(new FlogApiMVo());
            RequestStore.apiLog.get().setApiType(FieldConstant.FlogApiM.API_TYPE_I);
        }
        return RequestStore.apiLog.get();
    }
    
    /**
     * 设置接口日志对象 .
     *
     * @param flogApiMVo 日志对象
     * @author david.cui
     * @date 2021/12/28 16:44
     */
    public static void setApiLog(FlogApiMVo flogApiMVo) {
    	RequestStore.apiLog.set(flogApiMVo);
    }

    /**
     * 获取IP地址 .
     *
     * @return java.lang.String IP地址
     * @author andy.sher
     * @date 2018/9/10 14:02
     */
    public static String getIpAddress() {
        return GlobalStatus.getIpAddress();
    }

    /**
     * 设置小程序appID .
     *
     * @param appID 小程序appID
     * @return void
     * @author andy.sher
     * @date 2019/11/27 11:44
     */
    public static void setAppID(String appID) {
        RequestStore.appID.set(appID);
    }

    /**
     * 获取小程序appID .
     *
     * @param
     * @return java.lang.String 小程序appID
     * @author andy.sher
     * @date 2019/11/27 11:44
     */
    public static String getAppID() {
        return appID.get();
    }

    /**
     * 获取小程序appSecret .
     *
     * @param
     * @return java.lang.String 小程序appSecret
     * @author andy.sher
     * @date 2019/11/27 11:46
     */
    public static String getAppSecret() {
        if (StringUtils.isNotBlank(getAppID())) {
            return null;
        }
        WeixinPropertiesUtils weixinPropertiesUtils = SpringContextUtils.getBean(WeixinPropertiesUtils.class);
        return weixinPropertiesUtils.getAppSecretByAppid(getAppID());
    }

    /**
     * 获取品牌代码 .
     *
     * @param
     * @return java.lang.String 品牌代码
     * @author andy.sher
     * @date 2019/11/28 18:52
     */
    public static String getBrandCode() {
        return RequestStore.brandCode.get();
    }

    /**
     * 设置品牌代码 .
     *
     * @param brandCode 品牌代码
     * @return void
     * @author andy.sher
     * @date 2019/11/28 18:53
     */
    public static void setBrandCode(String brandCode) {
        RequestStore.brandCode.set(brandCode);
    }

    /**
     * 初始化请求分页参数
     *
     * @param reqEntity 请求对象
     * @author andy.sher
     * @date 2018/7/25 13:20
     */
    public static void initPageParam(ReqEntity reqEntity) {
        if (null != reqEntity.getPageNum()) {
            setPageNum(reqEntity.getPageNum());
        }
        if (null != reqEntity.getPageSize()) {
            setPageSize(reqEntity.getPageSize());
        }
        if (StringUtils.isNotBlank(reqEntity.getSort())) {
            // 防SQL注入
            String injStr = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
            String[] injStrArray = injStr.split("\\|");
            for (String str : injStrArray) {
                SmeAssert.lt(reqEntity.getSort().indexOf(str), 0, "你似乎有些不怀好意。");
            }
            setSort(JSON.parseObject(reqEntity.getSort()));
        }
    }

    /**
     * 销毁参数仓库
     *
     * @author andy.sher
     * @date 2018/7/25 16:23
     */
    public static void destroy() {
        pageNum.remove();
        pageSize.remove();
        sort.remove();
        token.remove();
        location.remove();
        userid.remove();
        cityCode.remove();
        client.remove();
        page.remove();
        language.remove();
        apiLog.remove();
        appID.remove();
        brandCode.remove();
    }

}
