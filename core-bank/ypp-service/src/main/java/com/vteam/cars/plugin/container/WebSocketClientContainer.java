package com.vteam.cars.plugin.container;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import jakarta.websocket.Session;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket客户端容器 .<br>
 *
 * @author andy.sher
 * @date 2018/11/6 9:59
 */
@Slf4j
@Component
public class WebSocketClientContainer {

    /**
     * 缓存
     */
    private Map<String, Session> cache;

    public WebSocketClientContainer() {
        cache = new ConcurrentHashMap<>();
    }

    /**
     * 清理session容器 .
     *
     * @param
     * @return void
     * @author andy.sher
     * @date 2019/6/19 13:22
     */
    @Scheduled(cron = "0/3 * * * * ?")
    private void clearContainer() {
        if (!this.cache.isEmpty()) {
            Iterator<Map.Entry<String, Session>> it = this.cache.entrySet().iterator();
            Map.Entry<String, Session> entry;
            while (it.hasNext()) {
                entry = it.next();
                if (!entry.getValue().isOpen()) {
                    it.remove();
                }
            }
        }
    }

    /**
     * 获取客户端 .
     *
     * @param key 键
     * @return java.lang.String 值
     * @author andy.sher
     * @date 2018/10/19 17:27
     */
    public Session get(@NotNull(message = "客户端标识符不能为空。") String key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        return null;
    }

    /**
     * 存入客户端 .<br>
     *
     * @param key     客户端标识符
     * @param session 客户端连接
     * @return void
     * @author andy.sher
     * @date 2018/8/1 19:20
     */
    public void set(@NotNull(message = "客户端标识符不能为空。") String key, Session session) {
        log.info("webSocketClientContainer 设置 key = {}, sessionIsOpen:{}" , key, session.isOpen());
        this.cache.put(key, session);
    }

    /**
     * 删除客户端 .
     *
     * @param key 客户端标识符
     * @return void
     * @author andy.sher
     * @date 2018/11/1 18:07
     */
    public void delete(@NotNull(message = "客户端标识符不能为空。") String key) {
        Iterator<Map.Entry<String, Session>> iterator = cache.entrySet().iterator();
        Map.Entry<String, Session> entry;
        while (iterator.hasNext()) {
            entry = iterator.next();
            if (entry.getKey().equals(key) && !entry.getValue().isOpen()) {
                try {
                    entry.getValue().close();
                } catch (IOException e) {
                    log.warn(WebSocketClientContainer.class.getName(), e);
                }
                iterator.remove();
            }
        }
    }

    /**
     * 判断客户端是否存在 .
     *
     * @param clientKey 客户端唯一标识符
     * @return boolean
     * @author andy.sher
     * @date 2018/11/6 10:16
     */
    public boolean hasClient(String clientKey) {
        return this.cache.containsKey(clientKey);
    }

}
