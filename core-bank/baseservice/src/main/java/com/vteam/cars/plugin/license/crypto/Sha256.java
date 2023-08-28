/**
 * 创建于: 2018年9月4日 下午5:48:53<br>
 * 所属项目:VTeam Framework
 * 文件名称:Sha256.java
 * 作者:oscar.yu
 * 版权信息:Copyright (c) 2001~2018, VTeam All Rights Reserved.
 */
package com.vteam.cars.plugin.license.crypto;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 类功能描述:Sha256加解密实现类
 * @className Sha256.java
 * 
 * @author oscar.yu
 * @version 1.0.0
 *
 * @history 2018年9月4日 oscar.yu 创建 Sha256.java
 */
public class Sha256 {

    /**
     * 加密字符串
     * @param plainText
     * @return
     */
    public static String encryptAsHex(String plainText) {
        return DigestUtils.sha256Hex(plainText);
    }

    /**
     * 加密字节数组
     * @param plainText
     * @return
     */
    public static byte[] encryptAsByte(byte[] plainData) {
        return DigestUtils.sha256(plainData);
    }

    /**
     * 加密字节数组
     * @param plainText
     * @return
     */
    public static String encryptAsHex(byte[] plainData) {
        return DigestUtils.sha256Hex(plainData);
    }
}
