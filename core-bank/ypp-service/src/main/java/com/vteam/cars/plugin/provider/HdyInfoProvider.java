package com.vteam.cars.plugin.provider;

import com.vteam.cars.entity.model.FipaHdyM;
import com.vteam.cars.entity.vo.FipaHdyMVo;
import com.vteam.cars.service.fipa.FipaHdyMService;
import com.vteam.vtarm.cache.MapContainer;
import com.vteam.vtarm.data.Provider;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 节假日缓存数据提供者 .<br>
 *
 * @author care.zheng
 */
@Component
public class HdyInfoProvider implements Provider {

    public static final String HDY = "hdy";


    @Resource(type = FipaHdyMService.class)
    private FipaHdyMService fipaHdyMService;

    @Resource
    private MapContainer mapContainer;

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
        // 缓存所有节假日信息
        FipaHdyMVo hdyCondition = new FipaHdyMVo();
        List<FipaHdyM> fipaHdyMList = fipaHdyMService.listFipaHdyMByConditon(hdyCondition);
        if (CollectionUtils.isNotEmpty(fipaHdyMList)) {
            for (FipaHdyM temp : fipaHdyMList) {
                mapContainer.put(HDY, temp.getHdyYear()+"-"+temp.getHdyMonth()+"-"+temp.getHdyDay(), "true");
            }
        }
    }

    @Override
    public boolean needRebuild() {
        return false;
    }
}
