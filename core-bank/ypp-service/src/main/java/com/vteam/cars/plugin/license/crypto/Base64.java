/**
 * 创建于: 2016年11月22日 下午5:54:01<br>
 * 所属项目:VTeam Framework
 * 文件名称:Base64.java
 * 作者:oscar.yu
 * 版权信息:Copyright (c) 2001~2012, VTeam All Rights Reserved.
 */
package com.vteam.cars.plugin.license.crypto;

import com.vteam.cars.util.StringUtils;

/**
 * 类功能描述:Base64编码/解码工具类
 * @className Base64.java
 * 
 * @author oscar.yu
 * @version 1.0.0
 * 
 * @history 2016年11月22日 oscar.yu 创建 Base64.java
 */
public class Base64 {

    /**
     * 编码字节数组为字节
     * @param plainData
     * @return
     */
    public static byte[] encodeAsByteArray(byte[] plainData) {
        return org.apache.commons.codec.binary.Base64.encodeBase64(plainData);
    }

    /**
     * 编码字节数组为字符
     * @param plainData
     * @return
     */
    public static String encodeAsString(byte[] plainData) {
        byte[] base64Data = encodeAsByteArray(plainData);
        return StringUtils.toUtf8String(base64Data);
    }

    /**
     * 编码字符串为字节
     * @param plainText
     * @return
     */
    public static byte[] encodeAsByteArray(String plainText) {
        byte[] plainData = plainText.getBytes();
        return encodeAsByteArray(plainData);
    }

    /**
     * 编码字符串为字符
     * @param plainText
     * @return
     */
    public static String encodeAsString(String plainText) {
        byte[] plainData = plainText.getBytes();
        return encodeAsString(plainData);
    }

    /**
     * 解码字符串为字节
     * @param base64String
     * @return
     */
    public static byte[] decodeAsByteArray(String base64String) {
        return org.apache.commons.codec.binary.Base64.decodeBase64(base64String);
    }

    /**
     * 解码字符串为字符
     * @param base64String
     * @return
     */
    public static String decodeAsString(String base64String) {
        byte[] plainData = decodeAsByteArray(base64String);
        return StringUtils.toUtf8String(plainData);
    }

    /**
     * 判断是否为Base64格式字符串
     * @param base64String
     * @return
     */
    public static boolean isBase64(String base64String) {
        return org.apache.commons.codec.binary.Base64.isBase64(base64String);
    }
}
