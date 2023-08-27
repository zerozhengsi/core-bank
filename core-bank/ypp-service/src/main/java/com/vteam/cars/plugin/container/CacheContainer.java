package com.vteam.cars.plugin.container;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.GlobalConstants;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

import jakarta.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 缓存容器 .<br>
 *
 * @author andy.sher
 * @date 2019/4/16 13:19
 */
public class CacheContainer {

    private Map<String, String> cache = new ConcurrentHashMap<>();

    @Resource(type = StringRedisTemplate.class)
    private StringRedisTemplate stringRedisTemplate;

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    /**
     * 根据键获取值 .
     *
     * @param name    组
     * @param key     缓存键
     * @param hashKey map键
     * @return void
     * @author andy.sher
     * @date 2019/6/20 10:31
     */
    protected Object getHashValue(String name, String key, String hashKey) {
        return this.stringRedisTemplate.opsForHash().get(getCacheKey(name, key), hashKey);
    }

    /**
     * 获取map .
     *
     * @param name 组
     * @param key  缓存键
     * @return void
     * @author andy.sher
     * @date 2019/6/20 10:31
     */
    protected Map<Object, Object> entries(String name, String key) {
        return this.stringRedisTemplate.opsForHash().entries(getCacheKey(name, key));
    }

    /**
     * 添加到map .
     *
     * @param name    组
     * @param key     缓存键
     * @param hashKey map键
     * @param value   值
     * @return void
     * @author andy.sher
     * @date 2019/6/20 10:31
     */
    protected void putHash(String name, String key, String hashKey, String value) {
        this.stringRedisTemplate.opsForHash().put(getCacheKey(name, key), hashKey, value);
    }

    /**
     * 添加到set .
     *
     * @param name  组
     * @param key   键
     * @param value 值
     * @return void
     * @author andy.sher
     * @date 2019/6/20 10:31
     */
    protected void addSet(String name, String key, String value) {
        this.stringRedisTemplate.opsForSet().add(getCacheKey(name, key), value);
    }

    /**
     * 获取set .
     *
     * @param name 组
     * @param key  键
     * @return java.util.Set<java.lang.String> set
     * @author andy.sher
     * @date 2019/6/20 10:32
     */
    protected Set<String> members(String name, String key) {
        return this.stringRedisTemplate.opsForSet().members(getCacheKey(name, key));
    }

    /**
     * 获取自增值 .
     *
     * @param name 组
     * @param key  键
     * @return long 增长后的值
     * @author andy.sher
     * @date 2019/6/19 18:02
     */
    protected long incr(String name, String key) {
        if (GlobalConstants.Flag.REDIS.equalsIgnoreCase(smeConfiguration.getCacheMode())) {
            return stringRedisTemplate.execute((RedisCallback<Long>) redisConnection ->
                    redisConnection.incr(getCacheKey(name, key).getBytes()));
        } else {
            if (!this.cache.containsKey(getCacheKey(name, key))) {
                this.cache.put(getCacheKey(name, key), "0");
            }
            Object value = this.cache.get(getCacheKey(name, key));
            Long v = Long.valueOf(value.toString());
            v++;
            this.cache.put(getCacheKey(name, key), v.toString());
            return v.longValue();
        }
    }

    /**
     * 获取自减值 .
     *
     * @param name 组
     * @param key  键
     * @return long 减小后的值
     * @author andy.sher
     * @date 2019/6/19 18:02
     */
    protected long decr(String name, String key) {
        if (GlobalConstants.Flag.REDIS.equalsIgnoreCase(smeConfiguration.getCacheMode())) {
            return stringRedisTemplate.execute((RedisCallback<Long>) redisConnection ->
                    redisConnection.decr(getCacheKey(name, key).getBytes()));
        } else {
            if (!this.cache.containsKey(getCacheKey(name, key))) {
                this.cache.put(getCacheKey(name, key), "0");
            }
            Object value = this.cache.get(getCacheKey(name, key));
            Long v = Long.valueOf(value.toString());
            v--;
            this.cache.put(getCacheKey(name, key), v.toString());
            return v.longValue();
        }
    }

    /**
     * 设置缓存值 .
     *
     * @param name  组
     * @param key   键
     * @param value 值
     * @return void
     * @author andy.sher
     * @date 2019/4/16 13:32
     */
    protected void set(String name, String key, String value) {
        if (GlobalConstants.Flag.REDIS.equalsIgnoreCase(smeConfiguration.getCacheMode())) {
            this.stringRedisTemplate.opsForValue().set(getCacheKey(name, key), value);
        } else {
            this.cache.put(getCacheKey(name, key), value);
        }
    }

    /**
     * 设置缓存值 .
     *
     * @param name    组
     * @param key     键
     * @param value   值
     * @param timeout 超时时间
     * @return void
     * @author andy.sher
     * @date 2019/4/16 13:32
     */
    protected void set(String name, String key, String value, long timeout) {
        if (GlobalConstants.Flag.REDIS.equalsIgnoreCase(smeConfiguration.getCacheMode())) {
            this.stringRedisTemplate.opsForValue().set(getCacheKey(name, key), value, timeout, TimeUnit.SECONDS);
        } else {
            this.cache.put(getCacheKey(name, key), value);
        }
    }

    /**
     * 设置缓存值（随机缓存时间） .
     *
     * @param name      组
     * @param key       键
     * @param value     值
     * @param minSecond 最小超时时间
     * @param maxSecond 最大超时时间
     * @return void
     * @author andy.sher
     * @date 2019/4/16 13:32
     */
    protected void set(String name, String key, String value, int minSecond, int maxSecond) {
        if (GlobalConstants.Flag.REDIS.equalsIgnoreCase(smeConfiguration.getCacheMode())) {
            Random random = new Random();
            int expire = random.ints(minSecond, (maxSecond + 1)).findFirst().getAsInt();
            this.stringRedisTemplate.opsForValue().set(getCacheKey(name, key), value, expire, TimeUnit.SECONDS);
        } else {
            this.cache.put(getCacheKey(name, key), value);
        }
    }

    /**
     * 设置缓存值（设置成功返回true，覆盖失败返回false） .
     *
     * @param name  组
     * @param key   键
     * @param value 值
     * @return void
     * @author andy.sher
     * @date 2019/4/16 13:32
     */
    protected boolean setIfAbsent(String name, String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(getCacheKey(name, key), value);
    }

    /**
     * 设置缓存值（设置成功返回true，覆盖失败返回false） .
     *
     * @param name    组
     * @param key     键
     * @param value   值
     * @param timeout 超时时间
     * @return void
     * @author andy.sher
     * @date 2019/4/16 13:32
     */
    protected boolean setIfAbsent(String name, String key, String value, long timeout) {
        return stringRedisTemplate.execute((RedisCallback<Boolean>) redisConnection -> redisConnection.set(getCacheKey(name, key).getBytes(), value.getBytes(), Expiration.seconds(timeout), RedisStringCommands.SetOption.ifAbsent()));
    }

    /**
     * 获取缓存值 .
     *
     * @param name
     * @param key
     * @return java.lang.Object
     * @author andy.sher
     * @date 2019/4/16 13:33
     */
    protected Object get(String name, String key) {
        if (GlobalConstants.Flag.REDIS.equalsIgnoreCase(smeConfiguration.getCacheMode())) {
            return this.stringRedisTemplate.opsForValue().get(getCacheKey(name, key));
        } else {
            return this.cache.get(getCacheKey(name, key));
        }
    }

    /**
     * 统配删除缓存值所在组 .
     *
     * @param name 组
     * @return void
     * @author andy.sher
     * @date 2019/4/16 13:44
     */
    protected void deleteBatch(String name) {
        Set<String> keys = this.stringRedisTemplate.keys(smeConfiguration.getApplicationName() + GlobalConstants.Symbol.COLON + name + GlobalConstants.Symbol.ASTERISK);
        if (CollectionUtils.isNotEmpty(keys)) {
            this.stringRedisTemplate.delete(keys);
        }
    }

    /**
     * 删除缓存值 .
     *
     * @param name 组
     * @param key  键
     * @return void
     * @author andy.sher
     * @date 2019/4/16 13:44
     */
    protected void delete(String name, String key) {
        if (GlobalConstants.Flag.REDIS.equalsIgnoreCase(smeConfiguration.getCacheMode())) {
            this.stringRedisTemplate.delete(getCacheKey(name, key));
        } else {
            this.cache.remove(getCacheKey(name, key));
        }
    }

    /**
     * 获取缓存的键(Key)值
     *
     * @param name
     * @param key
     * @return
     */
    private String getCacheKey(String name, String key) {
        return smeConfiguration.getApplicationName() + GlobalConstants.Symbol.COLON + name + GlobalConstants.Symbol.COLON + key;
    }

}
