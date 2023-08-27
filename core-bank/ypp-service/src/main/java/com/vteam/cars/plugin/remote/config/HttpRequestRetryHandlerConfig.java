package com.vteam.cars.plugin.remote.config;

import com.vteam.cars.config.SmeConfiguration;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.Resource;
import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

/**
 * HttpClient的重试处理机制
 *
 * @author zack.yin
 * @date 2018/7/11 16:21
 */
@Configuration
public class HttpRequestRetryHandlerConfig {


    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Bean
    public HttpRequestRetryHandler httpRequestRetryHandler() {
        // 请求重试
        final int retryTime = smeConfiguration.getRetryTime();
        return (IOException exception, int executionCount, org.apache.http.protocol.HttpContext context) -> {
            // Do not retry if over max retry count,如果重试次数超过了retryTime,则不再重试请求
            if (executionCount >= retryTime) {
                return false;
            }
            // 服务端断掉客户端的连接异常
            if (exception instanceof NoHttpResponseException) {
                return true;
            }
            // time out 超时重试
            if (exception instanceof InterruptedIOException) {
                return false;
            }
            // Unknown host
            if (exception instanceof UnknownHostException) {
                return false;
            }
            // Connection refused
            if (exception instanceof ConnectTimeoutException) {
                return false;
            }
            // SSL handshake exception
            if (exception instanceof SSLException) {
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            if (!(request instanceof HttpEntityEnclosingRequest)) {
                return true;
            }
            return false;
        };
    }
}