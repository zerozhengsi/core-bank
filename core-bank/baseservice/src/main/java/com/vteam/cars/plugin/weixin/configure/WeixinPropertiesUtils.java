package com.vteam.cars.plugin.weixin.configure;

import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.util.StringUtils;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.Map;

/**
 * 微信配置工具类.
 * 
 * @author oscar.yu
 * @date 2021年12月22日 上午10:54:20
 */
@Component
public class WeixinPropertiesUtils {

    @Resource(type = WeixinProperties.class)
    private WeixinProperties weixinProperties;

    /**
     * 根据小程序appid键值获取配置信息
     * 
     * @param appidKey
     * @return appid,secret
     * @author oscar.yu
     * @date 2021/12/22 11:02
     */
    public String[] getAppConfig(String appidKey) {
        Map<String, String> configMap = weixinProperties.getClients();
        if (null == configMap) {
            return null;
        }
        String appConfig = configMap.get(appidKey);
        if (StringUtils.isNotBlank(appConfig)) {
            String[] appConfigArr = appConfig.split(GlobalConstants.Symbol.SEMICOLON);
            return appConfigArr;
        }
        return null;
    }

    /**
     * 获取小程序appid对应的秘钥
     * 
     * @param appid
     * @return secret
     * @author oscar.yu
     * @date 2021/12/22 11:07
     */
    public String getAppSecretByAppid(String appid) {
        Map<String, String> configMap = weixinProperties.getClients();
        if (null == configMap) {
            return null;
        }
        for (String appConfig : configMap.values()) {
            String[] appConfigArr = appConfig.split(GlobalConstants.Symbol.SEMICOLON);
            if (null != appConfigArr && appConfigArr.length == 2) {
                String tmpAppid = appConfigArr[0];
                String secret = appConfigArr[1];
                if (tmpAppid.equals(appid)) {
                    return secret;
                }
            }
        }
        return null;
    }
}
