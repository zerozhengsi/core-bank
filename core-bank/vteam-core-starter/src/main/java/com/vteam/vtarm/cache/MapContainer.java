package com.vteam.vtarm.cache;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * map缓存容器 .<br>
 *
 * @author andy.sher
 * @date 2019/6/20 17:45
 */
public class MapContainer extends CacheContainer {

    /**
     * 根据键获取值 .
     *
     * @param h   缓存键
     * @param key map键
     * @return void
     * @author andy.sher
     * @date 2019/6/20 10:31
     */
    public Object getHashValue(String h, String key) {
        return getStringRedisTemplate().opsForHash().get(getCacheKey(h), key);
    }

    /**
     * 获取map .
     *
     * @param h 缓存键
     * @return void
     * @author andy.sher
     * @date 2019/6/20 10:31
     */
    public Map<Object, Object> entries(String h) {
        return getStringRedisTemplate().opsForHash().entries(getCacheKey(h));
    }

    /**
     * 添加到map .
     *
     * @param h     缓存键
     * @param key   map键
     * @param value 值
     * @return void
     * @author andy.sher
     * @date 2019/6/20 10:31
     */
    public void put(String h, String key, String value) {
        // 修复Redis键及值均不能为null的问题
        value = null == value ? StringUtils.EMPTY : value;
        getStringRedisTemplate().opsForHash().put(getCacheKey(h), key, value);
    }

    /**
     * 删除所有缓存 .
     *
     * @param
     * @return void
     * @author andy.sher
     * @date 2019/8/8 11:23
     */
    public void deleteByGroup(String group) {
        super.deleteBatch(group);
    }

    /**
     * 从map删除 .
     *
     * @param h    缓存键
     * @param keys map键
     * @return void
     * @author andy.sher
     * @date 2019/11/21 14:33
     */
    public void delete(String h, String... keys) {
        getStringRedisTemplate().opsForHash().delete(getCacheKey(h), keys);
    }

}
