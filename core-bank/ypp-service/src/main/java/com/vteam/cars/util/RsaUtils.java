package com.vteam.cars.util;

import com.alibaba.fastjson.JSONObject;
import com.vteam.vtarm.utils.AesUtils;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加解密工具类.
 *
 * @author zhuang.shao
 * @date 2019年1月29日 下午2:12:04
 */
@Slf4j
public class RsaUtils {

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * 密钥长度，DH算法的默认密钥长度是1024 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 公钥
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 私钥
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 签名算法模式.
     *
     * @author oscar.yu
     * @date 2021年11月24日 下午5:56:10
     */
    public enum SignMode {
        /** MD5withRSA，默认 */
        MD5("MD5withRSA"),
        /** SHA1WithRSA */
        SHA1("SHA1WithRSA");

        SignMode(String mode) {
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
     * 初始化密钥对
     *
     * @return Map 密钥对的集合
     */
    public static Map<String, Object> initKey() throws Exception {
        // 实例化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        // 初始化密钥生成器
        keyPairGenerator.initialize(KEY_SIZE);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 将密钥存储在map中
        Map<String, Object> keyMap = new HashMap<>(4);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * RSA私钥解密字符串
     *
     * @param encodeStr 加密后数据(BASE64编码)
     * @param privateKey 私钥
     * @return 解密后的数据
     * @author zhuang.shao
     * @date 2019年1月29日 下午2:31:52
     */
    public static String rsaDecode(String encodeStr, String privateKey) {
        String decodedStr = StringUtils.EMPTY;
        try {
            return privateDecrypt(encodeStr, privateKey);
        } catch (Exception e) {
            log.error("解密失败,encodeStr:" + encodeStr + ",privateKey:" + privateKey);
        }
        return decodedStr;
    }


    /**
     * 使用私钥签名数据签名(SHA1WithRSA)
     *
     * @param data 签名数据
     * @param privateKeyStr 私钥
     * @return boolean签名结果
     */
    public static String signSHA1(String data, String privateKeyStr) {
        return sign(data, privateKeyStr, SignMode.SHA1);
    }

    /**
     * 使用私钥签名数据
     *
     * @param data data数据
     * @param privateKeyStr 私钥
     * @param signMode 签名算法
     * @return boolean签名结果
     */
    public static String sign(String data, String privateKeyStr, SignMode signMode) {
        try {
            PrivateKey priKey = getPrivateKey(privateKeyStr);
            Signature signature = Signature.getInstance(signMode.getMode());
            signature.initSign(priKey);
            signature.update(data.getBytes());
            return Base64Utils.encodeToString(signature.sign());
        } catch (Exception e) {
            log.error("签名字符串时遇到异常", e);
        }
        return null;
    }

    /**
     * 使用公钥验签(SHA1WithRSA)
     *
     * @param data data数据
     * @param sign 签名
     * @param publicKeyStr  公钥
     * @return boolean验签结果
     */
    public static boolean verifySHA1(String data, String sign, String publicKeyStr) {
        return verify(data, sign, publicKeyStr, SignMode.SHA1);
    }

    /**
     * 使用公钥验签
     *
     * @param data data数据
     * @param sign 签名
     * @param publicKeyStr  公钥
     * @param signMode 签名算法模式
     * @return boolean验签结果
     */
    public static boolean verify(String data, String sign, String publicKeyStr, SignMode signMode) {
        try {
            PublicKey pubKey = getPublicKey(publicKeyStr);
            Signature signature = Signature.getInstance(signMode.getMode());
            signature.initVerify(pubKey);
            signature.update(data.getBytes());
            return signature.verify(Base64Utils.decodeToByte(sign));
        } catch (Exception e) {
            log.error("验签字符串时遇到异常", e);
        }
        return false;
    }

    /**
     * 私钥加密
     *
     * @param data data数据
     * @param privateKeyStr 私钥
     * @return
     */
    public static String privateEncrypt(String data, String privateKeyStr) {
        try {
            return privateEncrypt(data, getPrivateKey(privateKeyStr));
        } catch (Exception e) {
            log.error("加密字符串[" + data + "]时遇到异常", e);
        }
        return data;
    }

    /**
     * 私钥加密
     *
     * @param data
     * @param privateKey
     * @return
     */
    public static String privateEncrypt(String data, RSAPrivateKey privateKey) {
        try {
            byte[] dataByte = data.getBytes(StandardCharsets.UTF_8);
            Cipher cipher = getCipher(privateKey, Cipher.ENCRYPT_MODE);
            return Base64Utils.ENCODER.encodeToString(doFinal(cipher, Cipher.ENCRYPT_MODE, dataByte));
        } catch (Exception e) {
            log.error("加密字符串[" + data + "]时遇到异常", e);
        }
        return data;
    }

    /**
     * 公钥解密
     *
     * @param data
     * @param publicKeyStr
     * @return
     */
    public static String publicDecrypt(String data, String publicKeyStr) {
        try {
            return publicDecrypt(data, getPublicKey(publicKeyStr));
        } catch (Exception e) {
            log.error("解密字符串[" + data + "]时遇到异常", e);
        }
        return data;
    }

    /**
     * 公钥解密
     *
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicDecrypt(String data, RSAPublicKey publicKey) {
        try {
            byte[] dataBase64Byte = Base64Utils.decodeToByte(data);
            Cipher cipher = getCipher(publicKey, Cipher.DECRYPT_MODE);
            return new String(doFinal(cipher, Cipher.DECRYPT_MODE, dataBase64Byte), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("解密字符串[" + data + "]时遇到异常", e);
        }
        return data;
    }

    /**
     * 公钥加密
     *
     * @param data
     * @param publicKeyStr
     * @return
     */
    public static String publicEncrypt(String data, String publicKeyStr) {
        try {
            return publicEncrypt(data, getPublicKey(publicKeyStr));
        } catch (Exception e) {
            log.error("加密字符串[" + data + "]时遇到异常", e);
        }
        return data;
    }

    /**
     * 公钥加密
     *
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey) {
        try {
            byte[] dataByte = data.getBytes(StandardCharsets.UTF_8);
            Cipher cipher = getCipher(publicKey, Cipher.ENCRYPT_MODE);
            return Base64Utils.ENCODER.encodeToString(doFinal(cipher, Cipher.ENCRYPT_MODE, dataByte));
        } catch (Exception e) {
            log.error("加密字符串[" + data + "]时遇到异常", e);
        }
        return data;
    }

    /**
     * 私钥解密
     *
     * @param data
     * @param privateKeyStr
     * @return
     */
    public static String privateDecrypt(String data, String privateKeyStr) {
        try {
            return privateDecrypt(data, getPrivateKey(privateKeyStr));
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     *
     * @param data
     * @param privateKey
     * @return
     */
    public static String privateDecrypt(String data, RSAPrivateKey privateKey) {
        try {
            byte[] dataBase64Byte = Base64Utils.decodeToByte(data);
            Cipher cipher = getCipher(privateKey, Cipher.DECRYPT_MODE);
            return new String(doFinal(cipher, Cipher.DECRYPT_MODE, dataBase64Byte), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 得到公钥
     *
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64Utils.decodeToByte(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     *
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return getPrivateKey(Base64Utils.decodeToByte(privateKey));
    }

    /**
     * 得到私钥
     *
     * @param keyArray 密钥字符数组
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(byte[] keyArray) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyArray);
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 创建密码书
     *
     * @param key
     * @param opMode
     * @return
     */
    public static Cipher getCipher(Key key, int opMode) {
        Cipher cipher = null;
        try {
            // 创建密码书
            cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(opMode, key);
        } catch (Exception err) {
            log.error(AesUtils.class.getName(), err);
        }
        return cipher;
    }

    /**
     * 处理加解密
     *
     * @param cipher
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] doFinal(Cipher cipher, int opmode, byte[] data) throws Exception {
        // 获取处理的字符最大数量，加密
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = MAX_DECRYPT_BLOCK;
        } else {
            maxBlock = MAX_ENCRYPT_BLOCK;
        }
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > maxBlock) {
                cache = cipher.doFinal(data, offSet, maxBlock);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * maxBlock;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * 测试方法
     *
     * @param args
     * @author oscar.yu
     * @throws Exception
     * @date 2019/11/28 09:23
     */
    public static void main(String[] args) throws Exception {
        // 生成一对公私钥
        Map<String, Object> keyMap = RsaUtils.initKey();
        final Key prikey = (Key) keyMap.get(PRIVATE_KEY);
        final Key pubkey = (Key) keyMap.get(PUBLIC_KEY);
        // 获取一对公私钥字符串
        String privateKey = Base64Utils.encodeToString(prikey.getEncoded());
        String publicKey = Base64Utils.encodeToString(pubkey.getEncoded());
        System.out.println("一、获取公私钥方法测试");
        System.out.println("1.1.获取私钥：" + privateKey);
        System.out.println("1.2.获取公钥：" + publicKey);
        System.out.println("===================================================");
        // 原文
        String data = "test123";
        try {
            System.out.println("二、加解密基础测试");
            System.out.println("2.1.被加密数据原文：" + data);
            // 私钥加密
            RSAPrivateKey rsaPrivateKey = getPrivateKey(privateKey);
            String privateEncryptData = privateEncrypt(data, rsaPrivateKey);
            System.out.println("2.2.1.私钥加密值为：" + privateEncryptData);
            // 公钥解密
            RSAPublicKey rsaPublicKey = getPublicKey(publicKey);
            String publicDecryptData = publicDecrypt(privateEncryptData, rsaPublicKey);
            System.out.println("2.2.2.公钥解密方法1值为：" + publicDecryptData);
            System.out.println("2.2.3.公钥解密方法2值为：" + publicDecrypt(privateEncryptData, publicKey));
            // 公钥加密
            String publicEncryptData = publicEncrypt(data, rsaPublicKey);
            System.out.println("2.3.1.公钥加密值为：" + publicEncryptData);
            // 私钥解密
            String privateDecryptData = privateDecrypt(publicEncryptData, rsaPrivateKey);
            System.out.println("2.3.2.私钥解密方法1值为：" + privateDecryptData);
            System.out.println("2.3.3.私钥解密方法2值为：" + rsaDecode(publicEncryptData, privateKey));
            System.out.println("===================================================");

            System.out.println("三、加密请求业务数据模拟");
            // 1.准备业务数据
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orgId", "ORG10001");
            jsonObject.put("orgName", "测试企业信息");
            // 2.将业务参数转换成json字符串
            String jsonString = jsonObject.toString();
            // 3.随机生成单次请求加密密钥及偏移量
            // 秘钥，使用方法随机生成（16位）
            String aesKey = "abcde!@#$%123ABC";
            // 偏移量，使用方法随机生成（16位）
            String aesIv = "1234567890123456";
            // 4.通过aesKey及aesIv对请求明文(jsonString)做AES加密算法,生成通信密文(data)
            // AES加密用户数据
            String requestDataEnc = AesUtils.doEncrypt(jsonString, aesKey, aesIv);
            // 编码为base64，保证网络传输后的一致性
            String requestData = Base64Utils.encode(requestDataEnc);
            // 5.使用报备的RSA公钥加密aesKey生成AES密钥的RSA密文
            // 将AES密钥和偏移量组合为一个字符串
            String akv = aesKey + "-" + aesIv;
            // 客户端使用公钥对AES密钥偏移量进行加密
            String akvcode = RsaUtils.publicEncrypt(akv, rsaPublicKey);
            // 编码为base64，保证网络传输后的一致性
            String requestAkv = Base64Utils.encode(akvcode);
            System.out.println("3.1.1.请求数据data原文值为：" + jsonString);
            System.out.println("3.1.2.请求数据akv原文值为：" + akv);
            System.out.println("3.2.1.请求数据data值为：" + requestData);
            System.out.println("3.2.2.请求数据akv值为：" + requestAkv);
            System.out.println("===================================================");

            System.out.println("四、解密请求业务数据模拟");
            // 1.RSA解密对称加密的aes key 和 iv
            // 私钥
            String systemPrivateKey = privateKey;
            // 第一次base64解字符串
            String decryptKeyIv = Base64Utils.decode(requestAkv);
            // 第二次rsa解真实数据
            decryptKeyIv = RsaUtils.rsaDecode(decryptKeyIv, systemPrivateKey);
            String[] decryptAesKeyIv = decryptKeyIv.split("-");
            // 2.解密数据
            // 第一次base64解码字符串
            String decryptData = Base64Utils.decode(requestData);
            // 第二次aes解密真实数据
            decryptData = AesUtils.doDecrypt(decryptData, decryptAesKeyIv[0], decryptAesKeyIv[1]);
            System.out.println("4.1.1.解密请求数据akv值为：" + decryptKeyIv);
            System.out.println("4.1.2.解密请求数据data值为：" + decryptData);
            System.out.println("===================================================");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}
