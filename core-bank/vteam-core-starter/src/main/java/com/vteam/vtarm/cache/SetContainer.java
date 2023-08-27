package com.vteam.vtarm.cache;

import java.util.Set;

/**
 * set缓存容器 .<br>
 *
 * @author andy.sher
 * @date 2019/11/21 14:49
 */
public class SetContainer extends CacheContainer {

    /**
     * 添加到缓存 .
     *
     * @param key    缓存键
     * @param values 缓存值
     * @return void
     * @author andy.sher
     * @date 2019/11/21 14:51
     */
    public void add(String key, String... values) {
        getStringRedisTemplate().opsForSet().add(getCacheKey(key), values);
    }

    /**
     * 获取缓存集合 .
     *
     * @param key 缓存键
     * @return void
     * @author andy.sher
     * @date 2019/11/21 14:51
     */
    public Set<String> members(String key) {
        return getStringRedisTemplate().opsForSet().members(getCacheKey(key));
    }

}
