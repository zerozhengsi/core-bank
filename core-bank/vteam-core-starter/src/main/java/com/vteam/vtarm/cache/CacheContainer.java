package com.vteam.vtarm.cache;

import com.vteam.vtarm.CommonConstants;
import com.vteam.vtarm.cluster.TaskClusterLock;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

import jakarta.annotation.Resource;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存容器 .<br>
 *
 * @author andy.sher
 * @date 2019/4/16 13:19
 */
public class CacheContainer {

    @Resource(type = StringRedisTemplate.class)
    private StringRedisTemplate stringRedisTemplate;

    @Value("${spring.application.name}")
    private String root;

    @TaskClusterLock
    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    /**
     * 设置缓存值 .
     *
     * @param key   键
     * @param value 值
     * @return void
     * @author andy.sher
     * @date 2019/4/16 13:32
     */
    public void set(String key, String value) {
        getStringRedisTemplate().opsForValue().set(getCacheKey(key), value);
    }

    /**
     * 设置缓存值 .
     *
     * @param key     键
     * @param value   值
     * @param timeout 超时时间
     * @return void
     * @author andy.sher
     * @date 2019/4/16 13:32
     */
    public void set(String key, String value, long timeout) {
        getStringRedisTemplate().opsForValue().set(getCacheKey(key), value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存值（随机缓存时间） .
     *
     * @param key       键
     * @param value     值
     * @param minSecond 最小超时时间
     * @param maxSecond 最大超时时间
     * @return void
     * @author andy.sher
     * @date 2019/4/16 13:32
     */
    public void set(String key, String value, int minSecond, int maxSecond) {
        Random random = new Random();
        int expire = random.ints(minSecond, (maxSecond + 1)).findFirst().getAsInt();
        getStringRedisTemplate().opsForValue().set(getCacheKey(key), value, expire, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存值（设置成功返回true，覆盖失败返回false） .
     *
     * @param key   键
     * @param value 值
     * @return void
     * @author andy.sher
     * @date 2019/4/16 13:32
     */
    public boolean setIfAbsent(String key, String value) {
        return getStringRedisTemplate().opsForValue().setIfAbsent(getCacheKey(key), value);
    }

    /**
     * 设置缓存值（设置成功返回true，覆盖失败返回false） .
     *
     * @param key     键
     * @param value   值
     * @param timeout 超时时间
     * @return void
     * @author andy.sher
     * @date 2019/4/16 13:32
     */
    public boolean setIfAbsent(String key, String value, long timeout) {
        return getStringRedisTemplate().execute((RedisCallback<Boolean>) redisConnection -> redisConnection.set(getCacheKey(key).getBytes(), value.getBytes(), Expiration.seconds(timeout), RedisStringCommands.SetOption.ifAbsent()));
    }

    /**
     * 获取缓存值 .
     *
     * @param key 缓存键
     * @return java.lang.Object
     * @author andy.sher
     * @date 2019/4/16 13:33
     */
    public String get(String key) {
        return getStringRedisTemplate().opsForValue().get(getCacheKey(key));
    }

    /**
     * 通配删除缓存值所在组 .
     *
     * @param key 缓存键
     * @return void
     * @author andy.sher
     * @date 2019/4/16 13:44
     */
    public void deleteBatch(String key) {
        Set<String> keys = getStringRedisTemplate().keys(getCacheKey(key) + CommonConstants.Symbol.ASTERISK);
        if (CollectionUtils.isNotEmpty(keys)) {
            getStringRedisTemplate().delete(keys);
        }
    }

    /**
     * 删除缓存值 .
     *
     * @param key 键
     * @return void
     * @author andy.sher
     * @date 2019/4/16 13:44
     */
    public void delete(String key) {
        getStringRedisTemplate().delete(getCacheKey(key));
    }

    /**
     * 获取缓存的键(Key)值
     *
     * @param key
     * @return
     */
    protected String getCacheKey(String key) {
        return root + CommonConstants.Symbol.SEMICOLON + getClass().getSimpleName() + CommonConstants.Symbol.SEMICOLON + key;
    }

}
