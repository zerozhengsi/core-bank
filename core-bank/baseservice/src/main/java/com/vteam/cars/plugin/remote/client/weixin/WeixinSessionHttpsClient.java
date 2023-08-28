package com.vteam.cars.plugin.remote.client.weixin;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.constant.TimeoutConstants;
import com.vteam.cars.constant.WeiXinConstants;
import com.vteam.cars.plugin.remote.client.base.BaseHttpClient;
import com.vteam.cars.plugin.weixin.configure.WeixinPropertiesUtils;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.cache.StringValueContainer;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 微信小程序登录 .<br>
 *
 * @author andy.sher
 * @date 2019/11/22 9:31
 */
@Slf4j
@Service
public class WeixinSessionHttpsClient extends BaseHttpClient<String> {

    public static final String ERRCODE = "errcode";

    @Resource
    private StringValueContainer stringValueContainer;

    @Resource(type = WeixinPropertiesUtils.class)
    private WeixinPropertiesUtils weixinPropertiesUtils;

    @Override
    public String execute(String jsCode) {
        String result = stringValueContainer.get(jsCode);
        if (StringUtils.isBlank(result) || result.contains(ERRCODE)) {
            String[] appConfigArr = weixinPropertiesUtils.getAppConfig(WeiXinConstants.Xcx.CONST_APPID_KEY);
            String appidConfig = null;
            String secretConfig = null;
            if (null != appConfigArr && appConfigArr.length == 2) {
                appidConfig = appConfigArr[0];
                secretConfig = appConfigArr[1];
            }
            String appid = StringUtils.isNotBlank(RequestStore.getAppID()) ? RequestStore.getAppID() : appidConfig;
            String appSecret = StringUtils.isNotBlank(RequestStore.getAppSecret()) ? RequestStore.getAppSecret() : secretConfig;
            if (null != appid || null != appSecret) {
                String params = "appid=" + appid + "&secret=" + appSecret + "&js_code=" + jsCode + "&grant_type="
                        + WeiXinConstants.Xcx.CONST_GRANT_TYPE;
                String url = WeiXinConstants.Api.API_URL_JSCODE2SESSION + GlobalConstants.Symbol.QUESTION_EN + params;
                HttpGet httpGet = new HttpGet(url);
                result = super.doRequest(httpGet);
                stringValueContainer.set(jsCode, result, TimeoutConstants.VERIFICATION_CODE_TIMEOUT);
                log.info("请求微信服务  --> url: {}  && 返回结果 --> {}", url, result);
            } else {
                log.error("appid和appSecret未获取成功");
                return null;
            }
        }
        if (StringUtils.isBlank(result)) {
            return null;
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.toJSONString();
    }

    @Override
    protected Class<?> getId() {
        return WeixinSessionHttpsClient.class;
    }

    @Override
    protected String getName() {
        return "小程序登录凭证校验";
    }
}
