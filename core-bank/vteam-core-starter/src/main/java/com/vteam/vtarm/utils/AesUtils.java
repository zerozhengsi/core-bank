package com.vteam.vtarm.utils;

import com.vteam.vtarm.CommonConstants;
import com.vteam.vtarm.codec.CodecProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES加密工具类(进阶版) .<br>
 *
 * @author andy.sher
 * @date 2018/7/16 14:03
 * 
 * @history 1.1.0 2021/11/24 oscar.yu AES加密增加ECB模式
 */
@Slf4j
public final class AesUtils {

    private AesUtils() {
    }

    private static final String AES = "AES";

    private static final Integer BASE64_LENGTH = 4;

    /**
     * 数据加密模式 .
     *
     * @author andy.sher
     * @date 2018/9/21 11:32
     */
    public enum Mode {
        /** API接口 */
        API,
        /** 数据库 */
        DB
    }

    /**
     * 加密算法模式.
     * 
     * @author oscar.yu
     * @date 2021年11月24日 下午5:56:10
     */
    public enum CryptoMode {
        /** 密码分组连接模式（Cipher Block Chaining），默认 */
        CBC("AES/CBC/PKCS5Padding"),
        /** 电子密码本模式（Electronic CodeBook） */
        ECB("AES/ECB/PKCS5Padding");

        CryptoMode(String mode) {
            this.mode = mode;
        }

        /** 加密语法 */
        private String mode;

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }
    }

    /**
     * aes解密 .<br>
     *
     * @param encrypt 加密的字符串
     * @return java.lang.String 解密字符串
     * @author andy.sher
     * @date 2018/7/16 15:15
     */
    public static String decrypt(String encrypt, Mode mode) {
        CodecProperties codecProperties = SpringContextUtils.getBean(CodecProperties.class);
        final String dataSecurityKey = codecProperties.getAesKey();
        final String dataSecurityIv = codecProperties.getAesIv();
        if (StringUtils.isNotBlank(encrypt) && !NumberUtils.isDigits(encrypt)
                && (encrypt.length() % BASE64_LENGTH == 0) && encrypt.length() >= 24) {
            // API加密模式
            if (Mode.API.equals(mode)) {
                return doDecrypt(encrypt, dataSecurityKey, dataSecurityIv);
            }
            // DB加密模式
            else if (Mode.DB.equals(mode)) {
                if (codecProperties.isDbEnable()) {
                    return doDecrypt(encrypt, dataSecurityKey, dataSecurityIv);
                }
            }
        }
        return encrypt;
    }

    /**
     * aes加密 .<br>
     *
     * @param content 普通字符串
     * @return java.lang.String 加密后的字符串
     * @author andy.sher
     * @date 2018/7/16 15:16
     */
    public static String encrypt(String content, Mode mode) {
        CodecProperties codecProperties = SpringContextUtils.getBean(CodecProperties.class);
        final String dataSecurityKey = codecProperties.getAesKey();
        final String dataSecurityIv = codecProperties.getAesIv();
        if (StringUtils.isNotBlank(content)) {
            // API加密模式
            if (Mode.API.equals(mode)) {
                return doEncrypt(content, dataSecurityKey, dataSecurityIv);
            }
            // DB加密模式
            else if (Mode.DB.equals(mode)) {
                if (codecProperties.isDbEnable()) {
                    return doEncrypt(content, dataSecurityKey, dataSecurityIv);
                }
            }
        }
        return content;
    }

    /**
     * 执行解密(CBC，默认模式) .
     *
     * @param content  加密前的字符串
     * @param keyValue 秘钥
     * @param ivValue  偏移量
     * @return java.lang.String 加密后的字符串
     */
    public static String doDecrypt(String content, String keyValue, String ivValue) {
        return doDecrypt(content, keyValue, ivValue, CryptoMode.CBC);
    }

    /**
     * 执行解密微信小程序数据 .
     *
     * @param content  加密前的字符串
     * @param keyValue 密钥
     * @param ivValue  偏移量
     * @return java.lang.String 解密后的字符串
     */
    public static String doDecryptWx(String content, String keyValue, String ivValue) {
        byte[] key = Base64.getDecoder().decode(keyValue);
        byte[] iv = Base64.getDecoder().decode(ivValue);
        byte[] data = Base64.getDecoder().decode(content);
        return doDecrypt(data, key, iv);
    }

    /**
     * 执行解密(ECB) .
     *
     * @param content  加密前的字符串
     * @param keyValue 秘钥
     * @return java.lang.String 加密后的字符串
     */
    public static String doDecryptECB(String content, String keyValue) {
        return doDecrypt(content, keyValue, null, CryptoMode.ECB);
    }

    /**
     * 执行解密
     * 
     * @param content
     * @param keyValue
     * @param ivValue
     * @param cryptoMode
     * @return java.lang.String 解密后的字符串
     */
    public static String doDecrypt(String content, String keyValue, String ivValue, CryptoMode cryptoMode) {
        try {
            // 先用base64解密
            byte[] data = Base64.getDecoder().decode(content.getBytes(CommonConstants.Character.UTF_8));
            // 执行加解密
            byte[] original = doFinal(data, keyValue, ivValue, Cipher.DECRYPT_MODE, cryptoMode);
            return new String(original, CommonConstants.Character.UTF_8);
        } catch (Exception e) {
            // log.error(AesUtils.class.getName(), e);
            // 解密日志优化，不输出全部异常信息
            log.error("AesUtils.doDecrypt失败，原文信息：" + content);
        }
        return content;
    }

    /**
     * 执行解密(CBC，默认模式) .
     *
     * @param data 加密前的数据字节数组
     * @param key  秘钥
     * @param iv   偏移量
     * @return java.lang.String 加密后的字符串
     */
    public static String doDecrypt(byte[] data, byte[] key, byte[] iv) {
        return doDecrypt(data, key, iv, CryptoMode.CBC);
    }

    /**
     * 执行解密(ECB) .
     *
     * @param data 加密前的数据字节数组
     * @param key  秘钥
     * @return java.lang.String 加密后的字符串
     */
    public static String doDecryptECB(byte[] data, byte[] key) {
        return doDecrypt(data, key, null, CryptoMode.ECB);
    }

    /**
     * 执行解密
     * 
     * @param data
     * @param key
     * @param iv
     * @param cryptoMode
     * @return java.lang.String 加密后的字符串
     */
    public static String doDecrypt(byte[] data, byte[] key, byte[] iv, CryptoMode cryptoMode) {
        try {
            // 执行加解密
            byte[] original = doFinal(data, key, iv, Cipher.DECRYPT_MODE, cryptoMode);
            return new String(original, CommonConstants.Character.UTF_8);
        } catch (Exception e) {
            // log.error(AesUtils.class.getName(), e);
            // 解密日志优化，不输出全部异常信息
            log.error("AesUtils.doDecrypt失败，原文信息：" + Base64.getEncoder().encode(data));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 执行加密(CBC，默认模式) .
     *
     * @param content  加密前的字符串
     * @param keyValue 秘钥
     * @param ivValue  偏移量
     * @return java.lang.String 解密后的值
     */
    public static String doEncrypt(String content, String keyValue, String ivValue) {
        return doEncrypt(content, keyValue, ivValue, CryptoMode.CBC);
    }

    /**
     * 执行加密(ECB) .
     *
     * @param content  加密前的字符串
     * @param keyValue 秘钥
     * @return java.lang.String 加密后的值
     */
    public static String doEncryptECB(String content, String keyValue) {
        return doEncrypt(content, keyValue, null, CryptoMode.ECB);
    }

    /**
     * 执行加密
     * 
     * @param content
     * @param keyValue
     * @param ivValue
     * @param cryptoMode
     * @return java.lang.String 加密后的值
     */
    public static String doEncrypt(String content, String keyValue, String ivValue, CryptoMode cryptoMode) {
        try {
            byte[] data = content.getBytes(CommonConstants.Character.UTF_8);
            // 处理加解密
            byte[] encrypted = doFinal(data, keyValue, ivValue, Cipher.ENCRYPT_MODE, cryptoMode);
            // 此处使用Base64做转码
            return Base64.getEncoder().encodeToString(encrypted).replaceAll("\r\n", StringUtils.EMPTY);
        } catch (Exception e) {
            // log.error(AesUtils.class.getName(), e);
            // 解密日志优化，不输出全部异常信息
            log.error("AesUtils.doEncrypt失败，原文信息：" + content);
        }
        return content;
    }

    /**
     * 执行加密(CBC) .
     *
     * @param content 加密前的字符串
     * @param key     秘钥
     * @param iv      偏移量
     * @return java.lang.String 解密后的值
     */
    public static String doEncrypt(byte[] content, byte[] key, byte[] iv) {
        return doEncrypt(content, key, iv, CryptoMode.ECB);
    }

    /**
     * 执行加密(ECB) .
     *
     * @param content 加密前的字符串
     * @param key     秘钥
     * @return java.lang.String 解密后的值
     */
    public static String doEncryptECB(byte[] content, byte[] key) {
        return doEncrypt(content, key, null, CryptoMode.ECB);
    }

    /**
     * 执行加密 .
     *
     * @param content    加密前的字符串
     * @param key        秘钥
     * @param iv         偏移量
     * @param cryptoMode
     * @return java.lang.String 解密后的值
     */
    public static String doEncrypt(byte[] content, byte[] key, byte[] iv, CryptoMode cryptoMode) {
        try {
            // 处理加解密
            byte[] encrypted = doFinal(content, key, iv, Cipher.ENCRYPT_MODE, cryptoMode);
            // 此处使用Base64做转码
            return Base64.getEncoder().encodeToString(encrypted).replaceAll("\r\n", StringUtils.EMPTY);
        } catch (Exception e) {
            // log.error(AesUtils.class.getName(), e);
            // 解密日志优化，不输出全部异常信息
            log.error("AesUtils.doEncrypt失败，原文信息：" + Base64.getEncoder().encode(content));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 创建密码书(CBC，默认模式)
     * 
     * @param keyValue
     * @param ivValue
     * @param opmode
     * @return
     * @author oscar.yu
     * @date 2021/11/24 19:36
     */
    public static Cipher getCipher(String keyValue, String ivValue, int opmode) {
        return getCipher(keyValue, ivValue, opmode, CryptoMode.CBC);
    }

    /**
     * 创建密码书(CBC，默认模式)
     * 
     * @param key
     * @param iv
     * @param opmode
     * @return
     * @author oscar.yu
     * @date 2021/11/24 19:38
     */
    public static Cipher getCipher(byte[] key, byte[] iv, int opmode) {
        return getCipher(key, iv, opmode, CryptoMode.CBC);
    }

    /**
     * 创建密码书
     *
     * @param keyValue
     * @param ivValue
     * @param opmode
     * @param cryptoMode
     * @return
     * @author oscar.yu
     * @date 2019/9/9 13:53
     */
    public static Cipher getCipher(String keyValue, String ivValue, int opmode, CryptoMode cryptoMode) {
        Cipher cipher = null;
        try {
            byte[] key = keyValue.getBytes(CommonConstants.Character.ASCII);
            byte[] iv = null;
            if (StringUtils.isNotBlank(ivValue)) {
                iv = ivValue.getBytes(CommonConstants.Character.UTF_8);
            }
            cipher = getCipher(key, iv, opmode, cryptoMode);
        } catch (Exception err) {
            log.error(AesUtils.class.getName(), err);
        }
        return cipher;
    }

    /**
     * 创建密码书
     *
     * @param key
     * @param iv
     * @param opmode
     * @param cryptoMode
     * @return Cipher
     * @author oscar.yu
     * @date 2019/9/9 13:53
     */
    public static Cipher getCipher(byte[] key, byte[] iv, int opmode, CryptoMode cryptoMode) {
        Cipher cipher = null;
        try {
            // 创建密码书
            SecretKey secretKey = new SecretKeySpec(key, AES);
            if (CryptoMode.CBC.equals(cryptoMode)) {
                IvParameterSpec secureRandom = new IvParameterSpec(iv);
                cipher = Cipher.getInstance(cryptoMode.getMode());
                cipher.init(opmode, secretKey, secureRandom);
            } else if (CryptoMode.ECB.equals(cryptoMode)) {
                cipher = Cipher.getInstance(cryptoMode.getMode());
                cipher.init(opmode, secretKey);
            }
        } catch (Exception err) {
            log.error(AesUtils.class.getName(), err);
        }
        return cipher;
    }

    /**
     * 处理加解密
     *
     * @param data
     * @param keyValue
     * @param ivValue
     * @param opmode
     * @param cryptoMode
     * @return
     */
    public static byte[] doFinal(byte[] data, String keyValue, String ivValue, int opmode, CryptoMode cryptoMode) {
        byte[] result = null;
        try {
            // 创建密码书
            Cipher cipher = getCipher(keyValue, ivValue, opmode, cryptoMode);
            result = cipher.doFinal(data);
        } catch (Exception err) {
            log.error(AesUtils.class.getName(), err);
        }
        return result;
    }

    /**
     * 处理加解密
     *
     * @param data
     * @param key
     * @param iv
     * @param opmode
     * @param cryptoMode
     * @return
     */
    public static byte[] doFinal(byte[] data, byte[] key, byte[] iv, int opmode, CryptoMode cryptoMode) {
        byte[] result = null;
        try {
            // 创建密码书
            Cipher cipher = getCipher(key, iv, opmode, cryptoMode);
            result = cipher.doFinal(data);
        } catch (Exception err) {
            log.error(AesUtils.class.getName(), err);
        }
        return result;
    }

}
