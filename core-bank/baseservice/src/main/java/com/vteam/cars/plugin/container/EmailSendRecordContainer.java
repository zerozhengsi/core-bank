package com.vteam.cars.plugin.container;

import com.vteam.cars.exception.MSSmeBusinessException;
import com.vteam.cars.util.DateUtils;
import com.vteam.vtarm.cache.CacheContainer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 邮件延时发送记录容器 .<br>
 *
 * @author andy.sher
 * @date 2019/4/25 17:36
 */
@Component
public class EmailSendRecordContainer extends CacheContainer {

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
     *
     * @param key 结构“refcode~delayTime(YYYY-MM-DD HH24:MI:SS)”
     * @param value 需要延时发送的邮件记录
     */
    @Override
    public void set(String key, String value) {
        if(key.indexOf("~")>0) {
            this.getStringRedisTemplate().opsForValue().set(this.getCacheKey(key), value);
        }else{
            throw new MSSmeBusinessException("邮件延时发送记录容器KEY结构必须是refcode~delayTime(YYYY-MM-DD HH24:MI:SS)"+key);
        }
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
        this.getStringRedisTemplate().delete(key);
    }

    /**
     * 遍历
     * @return
     */
    public Map<String,String> getValues4Delay(){
        // 指定键的模式
        String pattern = "*";

        // 获取符合指定模式的所有键
        Set<String> keys = this.getStringRedisTemplate().keys(this.getCacheKey("") + "*");

        Map<String,String> valuesMap = new HashMap<String,String>();
        LocalDateTime currTime = DateUtils.getCurrDatetime();
        if(keys!=null && keys.size()>0) {
            // 遍历键的集合，获取对应的值
            for (String key : keys) {
                String[] item = key.split("~");
                LocalDateTime delayTime = LocalDateTime.parse(item[1], DateUtils.FMT_SECOND);
                if (delayTime.isBefore(currTime)) {
                    String value = this.getStringRedisTemplate().opsForValue().get(key);
                    valuesMap.put(key, value);
                }

            }
        }
        return valuesMap;
    }
}
