package com.vteam.cars.util;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * MD5加密工具类 .<br>
 *
 * @author andy.sher
 * @version 0.1.0
 */
@Slf4j
public final class MD5Utils {

    private MD5Utils() {
    }

    private static final String DIGEST_TYPE = "MD5";

    /**
     * 加密密码 .
     *
     * @param password 明文密码
     * @return java.lang.String 加密后的密码
     * @author andy.sher
     * @date 2019/3/8 17:27
     */
    public static String encryptPwd(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(DIGEST_TYPE);
            messageDigest.reset();
            SmeConfiguration smeConfiguration = SpringContextUtils.getBean(SmeConfiguration.class);
            messageDigest.update(password.getBytes(smeConfiguration.getEncoding()));
            byte[] byteArray = messageDigest.digest();
            StringBuffer md5StrBuff = new StringBuffer();
            for (int i = 0; i < byteArray.length; i++) {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
                }
            }
            return md5StrBuff.toString();
        } catch (Exception e) {
            log.error(MD5Utils.class.getName(), e);
        }

        return StringUtils.EMPTY;
    }

    /**
     * 计算文件MD5值 .
     *
     * @param file 文件
     * @return java.lang.String MD5值
     * @author andy.sher
     * @date 2019/3/8 17:29
     */
    public static String getFileMD5(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            return DigestUtils.md5Hex(fis);
        } catch (Exception e) {
            log.error(MD5Utils.class.getName(), e);
        }
        return StringUtils.EMPTY;
    }

}
