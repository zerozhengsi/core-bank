package com.vteam.vtarm.cluster;

import com.vteam.vtarm.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis 分布式锁 .<br>
 *
 * @author andy.sher
 * @date 2019/5/23 10:17
 */
@Slf4j
@Component
public class RedisClusterLock {

    @Resource(type = StringRedisTemplate.class)
    private StringRedisTemplate stringRedisTemplate;

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 加锁 .
     *
     * @param key        方法签名
     * @param value      锁的唯一标识
     * @param expireTime 过期时间
     * @return java.lang.Object 是否加锁成功
     * @author andy.sher
     * @date 2019/5/17 9:46
     */
    public boolean lock(String key, String value, int expireTime) {
        boolean success = stringRedisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.set(getRealKey(key).getBytes(), value.getBytes(), Expiration.from(expireTime, TimeUnit.SECONDS), RedisStringCommands.SetOption.SET_IF_ABSENT));
        if (success) {
            log.debug("获取锁成功 -> {}", key);
        } else {
            log.debug("获取锁失败 -> {}", key);
        }
        return success;
    }

    /**
     * 解锁 .
     *
     * @param key   方法签名
     * @param value 锁的唯一标识
     * @return java.lang.Object 是否解锁成功
     * @author andy.sher
     * @date 2019/5/17 9:46
     */
    public boolean unlock(String key, String value) {
        String scriptStr = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        DefaultRedisScript<Long> script = new DefaultRedisScript(scriptStr, Long.class);
        List<String> keys = new ArrayList<>();
        keys.add(getRealKey(key));
        Long result = stringRedisTemplate.execute(script, new StringRedisSerializer(), new RedisSerializer<Long>() {
            private final Charset charset = Charset.defaultCharset();

            @Override
            public byte[] serialize(Long aLong) throws SerializationException {
                return (aLong == null ? null : (aLong.toString()).getBytes(charset));
            }

            @Override
            public Long deserialize(byte[] bytes) throws SerializationException {
                return (bytes == null ? null : Long.parseLong(new String(bytes, charset)));
            }
        }, keys, value);

        boolean success = CommonConstants.ArabicNumeral.N1.equals(result.toString());
        if (success) {
            log.debug("释放锁成功 -> {}", key);
        }
        return success;
    }

    /**
     * 获取真实键 .
     *
     * @param key 键
     * @return java.lang.String 真实的键
     * @author andy.sher
     * @date 2019/5/23 10:29
     */
    private String getRealKey(String key) {
        return String.format("%s:%s", applicationName, key);
    }

}
