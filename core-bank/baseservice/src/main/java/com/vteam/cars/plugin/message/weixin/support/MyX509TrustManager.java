package com.vteam.cars.plugin.message.weixin.support;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 证书信任管理器类
 *
 * @author zhuang.shao
 * @date 2018年9月26日 下午4:35:45
 */
public class MyX509TrustManager implements X509TrustManager {

    /**
     * 检查客户端
     */
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    /**
     * 检查服务端
     */
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

}
