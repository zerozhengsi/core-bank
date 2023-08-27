package com.vteam.cars.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA1加密工具类.
 *
 * @author hang.xia
 * @date 2020/4/22 21:41
 */
public class Sha1Utils {

    /**
     * SHA1不可逆加密
     *
     * @param content
     * @return java.lang.String
     * @author hang.xia
     * @date 2020/4/22 21:58
     */
    public static String encode(String content) throws NoSuchAlgorithmException {
        byte[] input = content.getBytes();
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

}
