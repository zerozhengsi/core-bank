package com.vteam.vtarm.cache;

import org.springframework.data.redis.core.RedisCallback;

/**
 * 数字序列容器 .<br>
 *
 * @author andy.sher
 * @date 2019/6/20 9:16
 */
public class SequenceContainer extends SetContainer {

    /**
     * 获取下一个递增值 .
     *
     * @param key 键
     * @return long 递增值
     * @author andy.sher
     * @date 2019/6/20 9:18
     */
    public long incr(String key) {
        return getStringRedisTemplate().execute((RedisCallback<Long>) redisConnection ->
                redisConnection.incr(getCacheKey(key).getBytes()));
    }

    /**
     * 获取下一个递减值 .
     *
     * @param key 键
     * @return long 递减值
     * @author andy.sher
     * @date 2019/6/20 9:18
     */
    public long decr(String key) {
        return getStringRedisTemplate().execute((RedisCallback<Long>) redisConnection ->
                redisConnection.decr(getCacheKey(key).getBytes()));
    }

}
