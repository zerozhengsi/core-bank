package com.vteam.cars.plugin.provider;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.entity.vo.FipaAreMVo;
import com.vteam.cars.service.fipa.FipaAreMService;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.cache.MapContainer;
import com.vteam.vtarm.data.Provider;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 省市区信息启动初始化 .<br>
 *
 * @author andy.sher
 * @date 2019/6/14 13:13
 */
@Service
public class AreaInfoProvider implements Provider {

    @Resource( type = StringRedisTemplate.class)
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private SmeConfiguration smeConfiguration;

    /**
     * 地区对象FIPA_ARE_M<br>
     * KEY = AREA_CODE<br>
     * VALUE = 地区对象FIPA_ARE_M
     */
    public static final String AREA = "area";

    /**
     * 地区代号(或键值反转)<br>
     * KEY = PROVINCE_NAME + COUNTY_NAME<br>
     * VALUE = AREA_CODE
     */
    public static final String AREA_CODE = "area_code";
    
    /**
     * 省市描述<br>
     * KEY = PROVINCE_NAME + CITY_NAME<br>
     * VALUE = AREA_CODE
     */
    public static final String AREA_PROV_CITY = "area_prov_city";
    
    /**
     * 省市区描述(或键值反转)<br>
     * KEY = AREA_CODE<br>
     * VALUE = PROVINCE_NAME + CITY_NAME + 
     */
    public static final String AREA_PROV_CITY_COUNTY = "area_prov_city_county";
    
    /**
     * 区域类型对应省份集合字符串
     */
    public static final String AREA_TYPE_PROV = "area_type_prov";

    @Resource(type = FipaAreMService.class)
    private FipaAreMService fipaAreMService;

    @Resource
    private MapContainer mapContainer;
    
    /**
     * 直辖市：北京、天津、重庆、上海
     */
    final static String BEI_JING = "11";
    final static String TIAN_JING = "12";
    final static String CHONG_QING = "50";
    final static String SHANG_HAI = "31";
    
    final static List<String> directlyCity = Arrays.asList(BEI_JING, TIAN_JING, CHONG_QING, SHANG_HAI);

    @Override
    public String get(String... key) {
        if (ArrayUtils.isNotEmpty(key)) {
            Object value = mapContainer.getHashValue(key[0], key[1]);
            if (null != value) {
                return value.toString();
            }
        }
        return StringUtils.EMPTY;
    }

    @Override
    public void build() {
        // 缓存所有地区信息
        // toby 20230223 修改：以下代码改用批量模式插入缓存，避免短连接过多，耗时过长
        FipaAreMVo areCondition = new FipaAreMVo();
        RedisSerializer<String> keySerializer = (RedisSerializer<String>)stringRedisTemplate.getKeySerializer();
        RedisSerializer<Object> valueSerializer = (RedisSerializer<Object>)stringRedisTemplate.getValueSerializer();
        List<FipaAreMVo> areaList = fipaAreMService.listAreByCondition(areCondition);
        stringRedisTemplate.executePipelined((RedisCallback<Object>) pipeLine -> {
                StringBuilder redisKey = new StringBuilder();
                redisKey.append(smeConfiguration.getApplicationName() + ":");
                redisKey.append(MapContainer.class.getSimpleName() + ":");
                if (CollectionUtils.isNotEmpty(areaList)) {
                    for (FipaAreMVo temp : areaList) {
                        final String areaCode = temp.getAreaCode();
                        final String provinceName = temp.getProvinceName();
                        final String cityName = temp.getCityName();
                        final String countyName = temp.getCountyName();
                        // 省区
                        pipeLine.hSet(keySerializer.serialize(redisKey + AREA_CODE), keySerializer.serialize(provinceName + countyName), valueSerializer.serialize(areaCode));
                        pipeLine.hSet(keySerializer.serialize(redisKey  + AREA_CODE), keySerializer.serialize(areaCode), valueSerializer.serialize(provinceName + countyName));
                        // 省市描述
                        pipeLine.hSet(keySerializer.serialize(redisKey + AREA_PROV_CITY), keySerializer.serialize(areaCode), valueSerializer.serialize(this.getProvCityDesc(temp)));
                        pipeLine.hSet(keySerializer.serialize(redisKey + AREA), keySerializer.serialize(areaCode), valueSerializer.serialize(JSONObject.toJSONString(temp)));
                        // 省市区描述
                        pipeLine.hSet(keySerializer.serialize(redisKey + AREA_PROV_CITY_COUNTY), keySerializer.serialize(provinceName + cityName + countyName), valueSerializer.serialize(areaCode));
                        pipeLine.hSet(keySerializer.serialize(redisKey + AREA_PROV_CITY_COUNTY), keySerializer.serialize(areaCode), valueSerializer.serialize(provinceName + cityName + countyName));
                    }
                }
            return null;
        });
//        if (CollectionUtils.isNotEmpty(areaList)) {
//            for (FipaAreMVo temp : areaList) {
//                final String areaCode = temp.getAreaCode();
//                final String provinceName = temp.getProvinceName();
//                final String cityName = temp.getCityName();
//                final String countyName = temp.getCountyName();
//                // 省区
//                mapContainer.put(AREA_CODE, provinceName + countyName, areaCode);
//                mapContainer.put(AREA_CODE, areaCode, provinceName + countyName);
//                // 省市描述
//                mapContainer.put(AREA_PROV_CITY, areaCode, this.getProvCityDesc(temp));
//                mapContainer.put(AREA, areaCode, JSONObject.toJSONString(temp));
//                // 省市区描述
//                mapContainer.put(AREA_PROV_CITY_COUNTY, provinceName + cityName + countyName, areaCode);
//                mapContainer.put(AREA_PROV_CITY_COUNTY, areaCode, provinceName + cityName + countyName);
//            }
//        }
    }

    /**
     * 获取省市描述(直辖市仅显示省)
     * 
     * @param fipaAreMVo
     * @return
     */
    private String getProvCityDesc(FipaAreMVo fipaAreMVo) {
        String areaDesc;
        String provinceCode = fipaAreMVo.getAreaCode().substring(0, 2);
        // 如果是直辖市
        if (directlyCity.contains(provinceCode)) {
            areaDesc = fipaAreMVo.getProvinceName();
        } else {
            areaDesc = fipaAreMVo.getProvinceName() + fipaAreMVo.getCityName();
        }
        return areaDesc;
    }

    @Override
    public boolean needRebuild() {
        return false;
    }

}
