package com.vteam.cars.plugin.provider;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.cache.MapContainer;
import com.vteam.vtarm.data.Provider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

/**
 * I18N数据提供者 .<br>
 *
 * @author andy.sher
 * @date 2019/5/21 9:18
 */
@Slf4j
@Service
public class I18NResourceProvider implements Provider {

    public static final String I18N = "i18n";

    @Resource
    private MapContainer mapContainer;

    /**
     * 配置文件路径前缀
     */
    private final String location = "i18n/";

    @Override
    public String get(String... key) {
        Object value = mapContainer.getHashValue(I18N + GlobalConstants.Symbol.SEMICOLON + RequestStore.getLanguage(), key[0]);
        if (null != value) {
            return value.toString();
        }
        return key[0];
    }

    @Override
    public void build() {
        // 缓存国际化资源信息
        resolveI18N(location + "messages_zh_CN.json", GlobalConstants.Language.zh_CN);
        resolveI18N(location + "messages_zh_TW.json", GlobalConstants.Language.zh_TW);
        resolveI18N(location + "messages_en_US.json", GlobalConstants.Language.en_US);
    }

    @Override
    public boolean needRebuild() {
        return true;
    }

    /**
     * 解析I18N .
     *
     * @param path 源文件
     * @return void
     * @author andy.sher
     * @date 2019/5/14 9:16
     */
    private void resolveI18N(String path, String language) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource(path).getInputStream()));) {
            StringBuffer message = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                message.append(line);
            }
            if (StringUtils.isNotBlank(message.toString())) {
                JSONObject i18nData = JSONObject.parseObject(message.toString());
                Iterator<Map.Entry<String, Object>> i18nIterator = i18nData.entrySet().iterator();
                Map.Entry<String, Object> i18nEntry;
                while (i18nIterator.hasNext()) {
                    i18nEntry = i18nIterator.next();
                    mapContainer.put(I18N + GlobalConstants.Symbol.SEMICOLON + language, i18nEntry.getKey(), i18nEntry.getValue().toString());
                }
            }
        } catch (IOException e) {
            log.error(I18NResourceProvider.class.getName(), e);
        }
    }

}
