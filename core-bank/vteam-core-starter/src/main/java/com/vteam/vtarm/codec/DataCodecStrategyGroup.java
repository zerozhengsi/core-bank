package com.vteam.vtarm.codec;

import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.Getter;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 数据编解码处理器策略组 .<br>
 *
 * @author andy.sher
 * @date 2019/12/2 9:19
 */
@Getter
public class DataCodecStrategyGroup {

    private Map<String, DataCodecStrategy> dataCodecStrategyMap;

    @PostConstruct
    public void init() {
        // 获取所有数据处理器集合并设置处理器链
        Map<String, DataCodecStrategy> tempMap = SpringContextUtils.getBeansOfType(DataCodecStrategy.class);
        Map<String, DataCodecStrategy> handlerMap;
        if (null != tempMap && !tempMap.isEmpty()) {
            handlerMap = new HashMap<>(tempMap.size());
            Iterator<Map.Entry<String, DataCodecStrategy>> iterator = tempMap.entrySet().iterator();
            DataCodecStrategy dataCodecStrategy;
            while (iterator.hasNext()) {
                dataCodecStrategy = iterator.next().getValue();
                handlerMap.put(dataCodecStrategy.getId().getSimpleName(), dataCodecStrategy);
            }
        } else {
            handlerMap = new HashMap<>(1);
        }
        this.dataCodecStrategyMap = handlerMap;
    }

}
