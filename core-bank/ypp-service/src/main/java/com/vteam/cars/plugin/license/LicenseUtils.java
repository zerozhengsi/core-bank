/**
 * 创建于: 2017年6月30日 下午5:30:50<br>
 * 所属项目:VTeam Framework
 * 文件名称:LicenseUtils.java
 * 作者:oscar.yu
 * 版权信息:Copyright (c) 2001~2012, VTeam All Rights Reserved.
 */
package com.vteam.cars.plugin.license;

import com.vteam.cars.plugin.license.crypto.Aes;
import com.vteam.cars.plugin.license.crypto.Base64;
import com.vteam.cars.plugin.license.crypto.CipherUtils;
import com.vteam.cars.util.Base64Utils;
import com.vteam.cars.util.RsaUtils;
import com.vteam.cars.util.StringUtils;

import javax.crypto.Cipher;

/**
 * 类功能描述:系统授权许可工具类
 * @className LicenseUtils.java
 *
 * @author oscar.yu
 * @version 1.0.0
 *
 * @history 2017年6月30日 oscar.yu 创建 LicenseUtils.java
 */
public class LicenseUtils {

    /**
     * 分隔符
     */
    public static final String KEY_SEPARATOR = "-";

    /**
     * 万国码
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * 授权码中间变量
     */
    public static final String SMME_EMAIL = "harrison.yuan@vteamsystem.com";

    /**
     * 授权码KEY中间变量
     */
    public static final String SMME = "CEFA";
    
    /**
     * 临时关闭授权公钥
     */
    private static final String CLOSED_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMDX3brz7N2inICu1owpLXjI5hwBeq8DGka41RlZcxvXyD7hWEv9V0xdeRasLnMQZ4OG93qJ2R7Ns9QDYMOsqAsUeCHVGd8cjrFSS0yDpWfq4vvCuPUi+vrUxdxitXMoJDaHYOg7SK9CKjLwsVdEO6HDsNc2lqlwUbvCTSIQSXGwIDAQAB";

    /**
     * 解密许可证信息
     * @param plainText
     * @return
     */
    public static String decryptLicense(String plainText) {
        String license = null;
        if (StringUtils.isNotBlank(plainText) && plainText.length() > 24) {
            String key = plainText.substring(0, 24);
            String text = plainText.substring(24);
            byte[] keyArray = Base64.decodeAsByteArray(key);
            byte[] textArray = Base64.decodeAsByteArray(text);
            byte[] licenseArray = CipherUtils.doFinal(textArray, keyArray, Aes.ALGORITHM_AES, Aes.TRANSFORMATION_AES, Cipher.DECRYPT_MODE);
            license = StringUtils.toUtf8String(licenseArray);
        }
        return license;
    }
    
    /**
     * 解密临时关闭授权字符串
     * @param keyStr
     * @return
     * @author oscar.yu
     * @date 2019/11/28 09:30
     */
    public static String decryptClosedKey(String keyStr) {
        if (Base64Utils.isBase64(keyStr)) {
            return RsaUtils.publicDecrypt(keyStr, CLOSED_PUBLIC_KEY);
        } else {
            return StringUtils.EMPTY;
        }
    }
}
