package com.vteam.cars.plugin.web.websocket.config;

import com.vteam.cars.plugin.license.Authorization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket配置类 .<br>
 *
 * @author andy.sher
 * @date 2018/9/1 12:37
 */
@Configuration
public class WebSocketConfiguration {

    public WebSocketConfiguration() {
        super();
        // 增加许可证认证控制
        boolean hasAuthorized = Authorization.HAS_AUTHORIZED;
        if (!hasAuthorized) {
            System.exit(0);
            return;
        }
    }

    /**
     * 注册websocket终端服务类 .<br>
     *
     * @author andy.sher
     * @date 2018/9/1 16:47
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
