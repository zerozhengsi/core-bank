package com.vteam.cars.plugin.container;

import com.vteam.vtarm.cache.CacheContainer;
import org.springframework.stereotype.Component;

/**
 * 短信发送记录容器 .<br>
 *
 * @author andy.sher
 * @date 2019/4/25 17:36
 */
@Component
public class SmsSendRecordContainer extends CacheContainer {

    /**
     * 获取缓存值 .
     *
     * @param key 键
     * @return java.lang.String 值
     * @author andy.sher
     * @date 2019/4/15 18:09
     */
    @Override
    public String get(String key) {
        return super.get(key);
    }

    /**
     * 设置缓存 .
     *
     * @param key   键
     * @param value 值
     * @author andy.sher
     * @date 2019/4/15 18:08
     */
    @Override
    public boolean setIfAbsent(String key, String value, long expire) {
        return super.setIfAbsent(key, value, expire);
    }

    /**
     * 删除缓存 .
     *
     * @param key 键
     * @author andy.sher
     * @date 2019/4/15 18:07
     */
    @Override
    public void delete(String key) {
        super.delete(key);
    }

}
