/**
 * 创建于: 2018年7月21日 上午12:03:25<br>
 * 所属项目:VTeam Framework
 * 文件名称:Base64Utils.java
 * 作者:oscar.yu
 * 版权信息:Copyright (c) 2001~2018, VTeam All Rights Reserved.
 */
package com.vteam.cars.util;

import lombok.extern.slf4j.Slf4j;

import jakarta.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.regex.Pattern;

/**
 * 类功能描述:Base64工具类
 *
 * @author oscar.yu
 * @version 1.0.0
 * @className Base64Utils.java
 * @history 2018年7月21日 oscar.yu 创建 Base64Utils.java
 */
@Slf4j
public final class Base64Utils {

    private Base64Utils() {
    }

    /**
     * 编码器
     */
    public static final Encoder ENCODER = Base64.getEncoder();

    /**
     * 解码器
     */
    public static final Decoder DECODER = Base64.getDecoder();

    /**
     * BASE64解密 .<br>
     *
     * @param encrypt 字符串
     * @return java.lang.String 解密后的字符串
     * @author andy.sher
     * @date 2018/7/26 13:23
     */
    public static String decode(String encrypt) {
        return new String(DECODER.decode(encrypt.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * BASE64解密成byte字节
     *
     * @param encrypt
     * @author zhuang.shao
     * @date 2019年1月29日 下午2:23:40
     */
    public static byte[] decodeToByte(String encrypt) {
        return DECODER.decode(encrypt.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * BASE64编码 .<br>
     *
     * @param content 字符串
     * @return java.lang.String 加密后的字符串
     * @author andy.sher
     * @date 2018/7/26 13:25
     */
    public static String encode(String content) {
        return ENCODER.encodeToString(content.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Base64-encode the given byte array to a String.
     * 
     * @param src the original byte array (may be {@code null})
     * @return the encoded byte array as a UTF-8 String
     */
    public static String encodeToString(byte[] src) {
        if (src.length == 0) {
            return StringUtils.EMPTY;
        }
        return ENCODER.encodeToString(src);
    }

    /**
     * 将一张本地图片转化成Base64字符串 .<br>
     *
     * @param imgPath 图片路径
     * @return java.lang.String
     * @author andy.sher
     * @date 2018/8/2 9:46
     */
    public static String getImageStrFromPath(String imgPath) {
        byte[] data = null;
        try (InputStream in = Files.newInputStream(Paths.get(imgPath));) {
            data = new byte[in.available()];
            in.read(data);
            // 返回Base64编码过的字节数组字符串
            return ENCODER.encodeToString(data);
        } catch (IOException e) {
            log.error(Base64Utils.class.getName(), e);
        }
        return StringUtils.EMPTY;
    }


    /**
     * 将一张本地图片文件转化成Base64字符串 .
     *
     * @param file
     * @return java.lang.String
     * @author zack.yin
     * @date 2018/11/16 11:30
     */
    public static String getImageStrFromFile(File file) {
        byte[] data = null;
        try (InputStream in = Files.newInputStream(file.toPath());) {
            data = new byte[in.available()];
            in.read(data);
            // 返回Base64编码过的字节数组字符串
            return ENCODER.encodeToString(data);
        } catch (IOException e) {
            log.error(Base64Utils.class.getName(), e);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 判断字符串是否为Base64编码后的字符串 .
     *
     * @param str 字符串
     * @return boolean 是否为64编码
     * @author andy.sher
     * @date 2018/9/14 13:11
     */
    public static boolean isBase64(@NotNull String str) {
        if (NumberUtils.isInteger(str)) {
            return false;
        } else {
            String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
            return Pattern.matches(base64Pattern, str);
        }
    }

}
