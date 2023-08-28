package com.vteam.cars.plugin.provider;

import com.vteam.cars.entity.vo.FipaSysMVo;
import com.vteam.cars.service.fipa.FipaSysMService;
import com.vteam.vtarm.cache.MapContainer;
import com.vteam.vtarm.data.Provider;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 系统配置数据提供者 .<br>
 *
 * @author andy.sher
 * @date 2019/5/17 17:08
 */
@Service
public class SystemConfigProvider implements Provider {

    private static final String SYSTEM_CONFIG = "system_config";

    @Resource(type = FipaSysMService.class)
    private FipaSysMService fipaSysMService;

    @Resource
    private MapContainer mapContainer;


    @Override
    public String get(String... key) {
        if (ArrayUtils.isNotEmpty(key)) {
            Object value = mapContainer.getHashValue(SYSTEM_CONFIG, key[0]);
            if (null != value) {
                return value.toString();
            }
        }
        return StringUtils.EMPTY;
    }

    @Override
    public void build() {
        // 缓存系统配置信息
        List<FipaSysMVo> fipaSysMVoList = fipaSysMService.listSystemConfig();
        if (CollectionUtils.isNotEmpty(fipaSysMVoList)) {
            for (FipaSysMVo fipaSysMVo : fipaSysMVoList) {
                mapContainer.put(SYSTEM_CONFIG, fipaSysMVo.getParamName(), fipaSysMVo.getParamValue());
            }
        }
    }

    @Override
    public boolean needRebuild() {
        return true;
    }
}
