package com.vteam.vtarm.data;

import com.vteam.vtarm.utils.SpringContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 数据提供者 .<br>
 *
 * @author andy.sher
 * @date 2019/5/17 16:09
 */
@Service
public class DataProvider {

    /**
     * 获取数据 .
     *
     * @param clazz 数据提供者类型
     * @param key   键（委托给数据提供者）
     * @return java.lang.String 数据序列化后的字符串
     * @author andy.sher
     * @date 2019/5/17 16:31
     */
    public <T> String get(Class<? extends Provider> clazz, String... key) {
        Provider provider = SpringContextUtils.getBean(clazz);
        String value = provider.get(key);
        if (StringUtils.isBlank(value) && provider.needRebuild()) {
            provider.build();
            value = provider.get();
        }
        return value;
    }

    /**
     * 重新生成 .
     *
     * @param clazz 数据提供者类型
     * @return void
     * @author andy.sher
     * @date 2019/5/21 14:54
     */
    public void rebuild(Class<? extends Provider> clazz) {
        Provider provider = SpringContextUtils.getBean(clazz);
        provider.build();
    }
}
