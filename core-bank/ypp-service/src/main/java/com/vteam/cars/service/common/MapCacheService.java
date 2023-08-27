package com.vteam.cars.service.common;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.plugin.provider.ExgParamProvider;
import com.vteam.cars.plugin.provider.HdyInfoProvider;
import com.vteam.cars.plugin.provider.OtpInfoProvider;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.cache.MapContainer;
import com.vteam.vtarm.data.DataProvider;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 导出数据map公共服务类
 *
 * @author jiangming.huang
 * @date 2019/4/28 17:19
 */
@Service
public class MapCacheService {

    @Resource
    private DataProvider dataProvider;

    @Resource
    private MapContainer mapContainer;

    @Resource
    private SmeConfiguration smeConfiguration;

    /**
     * 本地缓存地区信息，用于优化导出频繁从Redis获取数据的性能问题
     */
    private ThreadLocal<Map<String, String>> exgLocalMap = new ThreadLocal<>();

    /**
     * 获取缓存 .
     *
     * @param key 键
     * @return java.util.Map 值
     * @author andy.sher
     * @date 2019/6/21 14:42
     */
    public Map<Object, Object> getMapCache(String key) {
        return mapContainer.entries(getRedisKey(key));
    }

    /**
     * 根据键获取缓存值 .
     *
     * @param cacheKey 键
     * @param keys     值（多个用逗号隔开）
     * @return java.lang.String
     * @author andy.sher
     * @date 2019/6/21 14:39
     */
    public String getValueByKey(String cacheKey, String keys) {
        if (StringUtils.isBlank(keys)) {
            return StringUtils.EMPTY;
        }
        // 循环从缓存中获取value
        String value = StringUtils.EMPTY;
        String desc = StringUtils.EMPTY;
        final String redisKey = this.getRedisKey(cacheKey);
        if (keys.indexOf(GlobalConstants.Symbol.COMMA) == -1) {
            desc = this.getRedisValue(redisKey, keys);
            value = desc;
        } else {
            for (String key : keys.split(GlobalConstants.Symbol.COMMA)) {
                desc = this.getRedisValue(redisKey, key);
                value += GlobalConstants.Symbol.COMMA + desc;
            }
            // 清理第一个逗号
            if (value.indexOf(GlobalConstants.Symbol.COMMA) >= 0) {
                value = value.substring(1);
            }
        }
        return value;
    }

    /**
     * 获取币种
     *
     * @param key
     * @return java.lang.String
     */
    public String getCurrency(String key) {
        return this.getValueByKey(FieldConstant.FspaExgM.CURRENCY, key);
    }
    /**
     * 获取是/否描述
     *
     * @param key
     * @return java.lang.String
     * @author jiangming.huang
     * @date 2019/4/28 17:24
     */
    public String getYesNo(String key) {
        return this.getValueByKey(FieldConstant.FspaExgM.YES_NO, key);
    }

    /**
     * 获取有无
     *
     * @param key
     * @return java.lang.String
     * @author jiangming.huang
     * @date 2019/5/8 9:21
     */
    public String getHaveOrNot(String key) {
        return this.getValueByKey(FieldConstant.FspaExgM.HAVE_OR_NOT, key);
    }


    /**
     * 所属区块
     *
     * @param key
     * @return java.lang.String
     * @author jiangming.huang
     * @date 2019/4/28 17:33
     */
    public String getAreaType(String key) {
        return this.getValueByKey(FieldConstant.FspaExgM.AREA_TYPE, key);
    }
    
    /**
     * 地址类型
     *
     * @param key
     * @return java.lang.String
     * @author jiangming.huang
     * @date 2019/4/28 17:33
     */
    public String getOrgAddressType(String key) {
    	return this.getValueByKey(FieldConstant.FspaExgM.ADDRESS_TYPE, key);
    }



    /**
     * 启用/禁用
     * @param key
     * @return
     */
    public String getUseFlag(String key) {
        return this.getValueByKey(FieldConstant.FspaExgM.USE_FLAG, key);
    }


    /**
     * 根据行业类别代号获取名称
     * 
     * @param otpcode
     * @return
     * @author oscar.yu
     * @date 2020/7/10 16:36
     */
    public String getOtpnameByOtpcode(String otpcode) {
        if (StringUtils.isBlank(otpcode)) {
            return null;
        }
        return dataProvider.get(OtpInfoProvider.class, OtpInfoProvider.OTP_NAME, otpcode);
    }

    /**
     * 缓存节假日
     *
     * @param date
     * @return
     * @author care.zheng
     */
    public String getHdyByDate(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        return dataProvider.get(HdyInfoProvider.class, HdyInfoProvider.HDY, date);
    }

    /**
     * 根据redis的键值及字典项代号获取对应的值
     * @param key
     * @return
     */
    private String getRedisValue(String redisKey, String key) {
        final String localKey = redisKey + GlobalConstants.Symbol.UNDERLINE + key;
        // 首先从本地缓存中获取
        String desc = this.getLocalValue(localKey);
        // 其次从Redis中获取
        if (StringUtils.isBlank(desc)) {
            desc = dataProvider.get(ExgParamProvider.class, redisKey, key);
            if (StringUtils.isBlank(desc)) {
                desc = key;
            }
            exgLocalMap.get().put(localKey, desc);
        }
        return desc;
    }

    /**
     * 获取封装后的key .
     *
     * @param key 原key
     * @return java.lang.String 封装后的key
     * @author andy.sher
     * @date 2019/6/21 11:49
     */
    private String getRedisKey(String key) {
        return ExgParamProvider.EXG_PARM + GlobalConstants.Symbol.SEMICOLON + smeConfiguration.getSystemLanguage() + GlobalConstants.Symbol.SEMICOLON + key;
    }

    /**
     * 获取本地缓存的信息(不存在返回null)
     * @param localKey
     * @return
     */
    private String getLocalValue(String localKey) {
        String value = null;
        Map<String, String> localMap = exgLocalMap.get();
        if (null == localMap) {
            localMap = new ConcurrentHashMap<>();
            exgLocalMap.set(localMap);
        } else {
            value = localMap.get(localKey);
        }
        return value;
    }


}
