package com.vteam.cars.plugin.remote.factory;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;


/**
 * HttpClient实例
 *
 * @author zack.yin
 * @date 2018/7/11 16:16
 */
@Service("httpClientManagerFactoryBean")
public class HttpClientManagerFactoryBean implements FactoryBean<CloseableHttpClient>, InitializingBean, DisposableBean {

    /**
     * FactoryBean生成的目标对象
     */
    private CloseableHttpClient client;

    @Resource(type = ConnectionKeepAliveStrategy.class)
    private ConnectionKeepAliveStrategy connectionKeepAliveStrategy;

    @Resource(type = HttpRequestRetryHandler.class)
    private HttpRequestRetryHandler httpRequestRetryHandler;

    @Resource(type = PoolingHttpClientConnectionManager.class)
    private PoolingHttpClientConnectionManager poolHttpcConnManager;

    @Resource(type = RequestConfig.class)
    private RequestConfig config;

    /**
     * 销毁上下文时，销毁HttpClient实例
     *
     * @param
     * @return void
     * @author zack.yin
     * @date 2018/7/12 11:37
     */
    @Override
    public void destroy() throws Exception {
        /*
         * 调用httpClient.close()会先shut down connection manager，然后再释放该HttpClient所占用的所有资源，
         * 关闭所有在使用或者空闲的connection包括底层socket。由于这里把它所使用的connection manager关闭了，
         * 所以在下次还要进行http请求的时候，要重新new一个connection manager来build一个HttpClient,
         * 也就是在需要关闭和新建Client的情况下，connection manager不能是单例的.
         */
        if (null != this.client) {
            this.client.close();
        }
    }

    /**
     * 初始化实例
     *
     * @param
     * @return void
     * @author zack.yin
     * @date 2018/7/12 11:38
     */
    @Override
    public void afterPropertiesSet() {
        /*
         * 建议此处使用HttpClients.custom的方式来创建HttpClientBuilder，而不要使用HttpClientBuilder.create()方法来创建HttpClientBuilder
         * 从官方文档可以得出，HttpClientBuilder是非线程安全的，但是HttpClients确实Immutable的，immutable 对象不仅能够保证对象的状态不被改变，
         * 而且还可以不使用锁机制就能被其他线程共享
         */
        final HttpClientBuilder builder = HttpClients.custom();
        builder.setSSLHostnameVerifier((a,b) -> {
            return true; // 证书校验通过
        });
        this.client = builder.setConnectionManager(poolHttpcConnManager)
                .setRetryHandler(httpRequestRetryHandler)
                .setKeepAliveStrategy(connectionKeepAliveStrategy)
                .setDefaultRequestConfig(config)
                .build();
    }

    /**
     * 返回实例的类型
     *
     * @param
     * @return org.apache.http.impl.client.CloseableHttpClient
     * @author zack.yin
     * @date 2018/7/12 11:38
     */
    @Override
    public CloseableHttpClient getObject() {
        return this.client;
    }

    @Override
    public Class<?> getObjectType() {
        return (this.client == null ? CloseableHttpClient.class : this.client.getClass());
    }

    /**
     * 构建的实例为单例
     *
     * @param
     * @return boolean
     * @author zack.yin
     * @date 2018/7/12 11:38
     */
    @Override
    public boolean isSingleton() {
        return true;
    }

}
