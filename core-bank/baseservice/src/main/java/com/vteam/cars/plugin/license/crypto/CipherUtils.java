/**
 * 创建于: 2017年6月30日 下午2:06:34<br>
 * 所属项目:VTeam Framework
 * 文件名称:CipherUtils.java
 * 作者:oscar.yu
 * 版权信息:Copyright (c) 2001~2012, VTeam All Rights Reserved.
 */
package com.vteam.cars.plugin.license.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * 类功能描述:加密处理工具类
 * @className CipherUtils.java
 *
 * @author oscar.yu
 * @version 1.0.0
 *
 * @history 2017年6月30日 oscar.yu 创建 CipherUtils.java
 */
public class CipherUtils {

    /**
     * 处理加解密
     */
    public static byte[] doFinal(byte[] data, byte[] key, String algorithm, String transformation, int opmode) {
        byte[] result = null;
        try {
            SecretKey secretKey = new SecretKeySpec(key, algorithm);
            SecureRandom secureRandom = new SecureRandom();
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(opmode, secretKey, secureRandom);
            result = cipher.doFinal(data);
        } catch (Exception err) {
            throw new RuntimeException("加解密失败，直接返回传入数据");
        }
        return result;
    }

}
