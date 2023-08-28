package com.vteam.cars.plugin.remote.config;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.plugin.message.weixin.support.MyX509TrustManager;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * 连接池管理
 *
 * @author zack.yin
 * @date 2018/7/12 10:12
 */
@Configuration
public class PoolingHttpClientConnectionManagerConfig {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Resource(type = MyX509TrustManager.class)
    private MyX509TrustManager myX509TrustManager;

    @Bean
    public PoolingHttpClientConnectionManager poolingClientConnectionManager() {
        // 增加https(非受信任证书)支持
        SSLConnectionSocketFactory sslSocketFactory = null;
        try {
            TrustManager[] trustManager = { myX509TrustManager };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, trustManager, new java.security.SecureRandom());
            String[] supportedProtocols = new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2" };
            sslSocketFactory = new SSLConnectionSocketFactory(sslContext, supportedProtocols, null, NoopHostnameVerifier.INSTANCE);
        } catch (NoSuchProviderException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (KeyManagementException e) {
        }
        // http请求
        ConnectionSocketFactory plainSocketFactory = PlainConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", plainSocketFactory).register("https", sslSocketFactory).build();

        PoolingHttpClientConnectionManager poolHttpcConnManager = new PoolingHttpClientConnectionManager(registry);
        // PoolingHttpClientConnectionManager poolHttpcConnManager = new PoolingHttpClientConnectionManager(smeConfiguration.getTimeToLive(), TimeUnit.SECONDS);
        // 最大连接数
        poolHttpcConnManager.setMaxTotal(smeConfiguration.getConnMaxTotal());
        // 路由基数
        poolHttpcConnManager.setDefaultMaxPerRoute(smeConfiguration.getMaxPerRoute());
        return poolHttpcConnManager;
    }
}
