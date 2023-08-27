/**
 * 创建于: 2017年6月30日 下午2:03:33<br>
 * 所属项目:VTeam Framework
 * 文件名称:Aes.java
 * 作者:oscar.yu
 * 版权信息:Copyright (c) 2001~2012, VTeam All Rights Reserved.
 */
package com.vteam.cars.plugin.license.crypto;

import javax.crypto.Cipher;

/**
 * 类功能描述:Aes加解密实现类
 * @className Aes.java
 * 
 * @author oscar.yu
 * @version 1.0.0
 * 
 * @history 2017年6月30日 oscar.yu 创建 Aes.java
 */
public class Aes {

    /**
     * 加解密算法
     */
    public static String ALGORITHM_AES = "AES";

    /**
     * 加解密转换方式
     */
    public static String TRANSFORMATION_AES = "AES";

    /**
     * 加密字符串为字符
     * @param data
     * @param key
     * @return
     */
    public static String encryptAsString(String data, String key) {
        try {
            byte[] bt = encrypt(data.getBytes(), key.getBytes());
            return Base64.encodeAsString(bt);
        } catch (RuntimeException err) {
            // 加解密失败，直接返回传入数据
            return data;
        }
    }

    /**
     * 解密字符串为字节
     * @param data
     * @param key
     * @return
     */
    public static byte[] decryptAsByteArray(String data, String key) {
        try {
            if (data == null) {
                return null;
            } else {
                if (!Base64.isBase64(data)) {
                    return data.getBytes();
                }
                byte[] buf = Base64.decodeAsByteArray(data);
                return decrypt(buf, key.getBytes());
            }
        } catch (RuntimeException err) {
            // 加解密失败，直接返回传入数据
            return data.getBytes();
        }
    }

    /**
     * 解密字符串为字符
     * @param data
     * @param key
     * @return
     */
    public static String decryptAsString(String data, String key) {
        if (data == null) {
            return null;
        } else {
            byte[] bt = decryptAsByteArray(data, key);
            return new String(bt);
        }
    }

    /**
     * 加密
     * @param data
     * @param key
     * @return
     */
    private static byte[] encrypt(byte[] data, byte[] key) {
        return CipherUtils.doFinal(data, key, ALGORITHM_AES, TRANSFORMATION_AES, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     * @param data
     * @param key
     * @return
     */
    private static byte[] decrypt(byte[] data, byte[] key) {
        return CipherUtils.doFinal(data, key, ALGORITHM_AES, TRANSFORMATION_AES, Cipher.DECRYPT_MODE);
    }

}
