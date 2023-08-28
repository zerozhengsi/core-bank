package com.vteam.cars.plugin.container;

import com.vteam.cars.constant.GlobalConstants;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * map缓存容器 .<br>
 *
 * @author andy.sher
 * @date 2019/6/20 17:45
 */
@Component
public class MapcacheContainer extends CacheContainer {

    private String CACHE_KEY = MapcacheContainer.class.getSimpleName();

    /**
   * 根据键获取值 .
   *
   * @param key     缓存键
   * @param hashKey map键
   * @return void
   * @author andy.sher
   * @date 2019/6/20 10:31
   */
  public Object getHashValue(String key, String hashKey) {
    return super.getHashValue(CACHE_KEY, key, hashKey);
  }

    /**
     * 获取map .
     *
     * @param key 缓存键
     * @return void
     * @author andy.sher
     * @date 2019/6/20 10:31
     */
    public Map<Object, Object> entries(String key) {
        return super.entries(CACHE_KEY, key);
    }

    /**
     * 添加到map .
     *
     * @param key     缓存键
     * @param hashKey map键
     * @param value   值
     * @return void
     * @author andy.sher
     * @date 2019/6/20 10:31
     */
    public void putHash(String key, String hashKey, String value) {
        super.putHash(CACHE_KEY, key, hashKey, value);
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
        super.deleteBatch(CACHE_KEY + GlobalConstants.Symbol.COLON + group + GlobalConstants.Symbol.COLON);
    }

}
