/**
 * 创建于: 2017年6月30日 下午2:38:29<br>
 * 所属项目:VTeam Framework
 * 文件名称:License.java
 * 作者:oscar.yu
 * 版权信息:Copyright (c) 2001~2012, VTeam All Rights Reserved.
 */
package com.vteam.cars.plugin.license;

import com.vteam.cars.plugin.license.crypto.Sha256;
import com.vteam.cars.plugin.license.util.DeviceInfoUtils;
import com.vteam.cars.plugin.license.util.MacUtils;
import com.vteam.cars.util.Base64Utils;
import com.vteam.cars.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 类功能描述:许可证处理对象
 * @className License.java
 *
 * @author oscar.yu
 * @version 1.0.0
 *
 * @history 2017年6月30日 oscar.yu 创建 License.java
 */
@Slf4j
public final class License {

    /**
     * 许可证文件路径
     */
    public static final String LICENSE_PATH = "license/license.CR";

    /**
     * 临时关闭文件路径
     */
    public static final String CLOSED_PATH = "license/Closed.key";

    /**
     * 许可证解密后信息
     */
    private static String licenseText = StringUtils.EMPTY;

    /**
     * 临时关闭信息
     */
    private static String closedKeystr = StringUtils.EMPTY;

    /**
     * 临时关闭关键字
     */
    private static String closedKeyword = StringUtils.EMPTY;

    /**
     * 处理器序列号
     */
    static String processorNum = DeviceInfoUtils.generateProcessorSerialNumber();

    /**
     * 硬盘序列号
     */
    static List<String> hardwareNums = DeviceInfoUtils.generateHardwareSerialNumbers();

    /**
     * 验证及读取许可证
     */
    public static void init() {
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = contextClassLoader.getResourceAsStream(LICENSE_PATH);
            if (null != input) {
                List<String> lines = IOUtils.readLines(input, LicenseUtils.UTF_8);
                if (CollectionUtils.isNotEmpty(lines)) {
                    String licenseCR = lines.get(0);
                    // license.CR解密处理
                    licenseText = LicenseUtils.decryptLicense(licenseCR);
                }
            }
            if (StringUtils.isBlank(licenseText)) {
                printAndLogError("授权失败：获取license.CR文件失败。");
                System.exit(0);
            }
            // 获取临时关闭文件
            InputStream closedInput = contextClassLoader.getResourceAsStream(CLOSED_PATH);
            if (null != closedInput) {
                List<String> lines = IOUtils.readLines(closedInput, LicenseUtils.UTF_8);
                if (CollectionUtils.isNotEmpty(lines)) {
                    String closedKeyline1 = lines.get(0).replaceAll("\r\n", StringUtils.EMPTY);
                    String closedKeyline2 = lines.get(1).replaceAll("\r\n", StringUtils.EMPTY);
                    // Closed.key解密处理
                    closedKeystr = LicenseUtils.decryptClosedKey(closedKeyline1);
                    closedKeyword = Base64Utils.decode(closedKeyline2) + licenseText;
                }
            }
        } catch (IOException err) {
            printAndLogError("授权失败：获取license.CR文件失败。");
            System.exit(0);
        }
    }

    /**
     * 获取许可证代号
     * @return
     */
    public static final String getLicenseid() {
        if (StringUtils.isBlank(licenseText)) {
            return null;
        }
        return licenseText.split(LicenseUtils.KEY_SEPARATOR)[0];
    }

    /**
     * 获取许可证到期日
     * @return
     */
    public static final Date getLicenseExpireDate() {
        if (StringUtils.isBlank(licenseText)) {
            return null;
        }
        String dateString = licenseText.split(LicenseUtils.KEY_SEPARATOR)[1];
        Date expireDate = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            expireDate = dateFormat.parse(dateString);
        } catch (Exception err) {
        }
        return expireDate;
    }

    /**
     * 获取许可证限制数量
     * @return
     */
    public static final String getLicenseLimitNum() {
        if (StringUtils.isBlank(licenseText)) {
            return null;
        }
        return licenseText.split(LicenseUtils.KEY_SEPARATOR)[2];
    }

    /**
     * 获取许可证产品名称
     * @return
     */
    public static final String getLicenseProductName() {
        if (StringUtils.isBlank(licenseText)) {
            return null;
        }
        return licenseText.split(LicenseUtils.KEY_SEPARATOR)[3];
    }

    /**
     * 获取本机匹配到的Mac地址
     * @return
     */
    public static String getLocalMac() {
        // 获取认证码信息
        String serverid = StringUtils.EMPTY;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = contextClassLoader.getResourceAsStream(Authorization.AUTHORIZATION_PATH);
        if (null != input) {
            List<String> lines = null;
            try {
                lines = IOUtils.readLines(input, LicenseUtils.UTF_8);
            } catch (IOException ignored) {
            }
            if (CollectionUtils.isNotEmpty(lines) && lines.size() >= 2) {
                serverid = lines.get(0);
            }
        }
        // 获取本机匹配到的Mac地址
        List<String> macList = getLocalMacList();
        assert macList != null;
        String mac = macList.get(0);
        if (StringUtils.isBlank(serverid)) {
            return mac;
        }
        for (String s : macList) {
            if (serverid.equals(getServiceId(buildMacWith16DeviceInfo(s)))) {
                mac = s;
                break;
            }
        }
        return mac;
    }

    /**
     * 获取本机Mac地址列表
     * @return
     */
    private static List<String> getLocalMacList() {
        List<String> macs = MacUtils.getLocalMacStringList();
        if (!macs.isEmpty()) {
            Collections.sort(macs);
        }
        return macs;
    }

    /**
     * 获取认证码信息
     * @param mac
     * @return
     */
    public static String getServiceId(String mac) {
        String licenceid = getLicenseid();
        String serverid = StringUtils.EMPTY;
        if ((StringUtils.isNotBlank(mac)) && (StringUtils.isNotBlank(licenceid))) {
            StringBuffer sb = new StringBuffer();
            String[] macElements = mac.split(LicenseUtils.KEY_SEPARATOR);
            sb.append(licenceid.substring(6)).append(macElements[2]).append(LicenseUtils.KEY_SEPARATOR);
            sb.append(macElements[0]).append(licenceid.substring(0, 2)).append(LicenseUtils.KEY_SEPARATOR);
            sb.append(macElements[4]).append(licenceid.substring(2, 4)).append(LicenseUtils.KEY_SEPARATOR);
            sb.append(macElements[1]).append(macElements[3]).append(LicenseUtils.KEY_SEPARATOR);
            sb.append(macElements[5]).append(licenceid.substring(4, 6)).append(LicenseUtils.KEY_SEPARATOR);

            sb.append(macElements[6]).append(macElements[7]);
            serverid = sb.toString().toUpperCase();
        }
        return serverid;
    }

    /**
     * 获取经过处理的MAC地址
     * @param mac
     * @return
     */
    public static String buildMacWithDeviceInfo(String mac) {
        String hardware = StringUtils.EMPTY;
        if (!hardwareNums.isEmpty()) {
            hardware = (String) hardwareNums.get(0);
        }
        String encryptStr = Sha256.encryptAsHex(mac + processorNum + hardware).substring(0, 12);
        String newMac = StringUtils.EMPTY;
        for (int i = 0; i < encryptStr.length() / 2; ++i) {
            newMac = newMac + encryptStr.substring(i * 2, i * 2 + 2) + "-";
        }
        return newMac.substring(0, newMac.length() - 1).toUpperCase();
    }

    /**
     * 获取经过处理的MAC地址
     * @param mac
     * @return
     */
    public static String buildMacWith16DeviceInfo(String mac) {
        String hardware = StringUtils.EMPTY;
        if (!hardwareNums.isEmpty()) {
            hardware = (String) hardwareNums.get(0);
        }
        String encryptStr = Sha256.encryptAsHex(mac + processorNum + hardware).substring(0, 16);
        String newMac = StringUtils.EMPTY;
        for (int i = 0; i < encryptStr.length() / 2; ++i) {
            newMac = newMac + encryptStr.substring(i * 2, i * 2 + 2) + "-";
        }
        return newMac.substring(0, newMac.length() - 1).toUpperCase();
    }
    
    /**
     * 获取临时关闭字符串
     * @return
     * @author oscar.yu
     * @date 2019/11/28 09:38
     */
    public static String getClosedKeystr() {
        return closedKeystr;
    }
    
    /**
     * 获取临时关闭授权码
     * @return
     * @author oscar.yu
     * @date 2019/11/28 09:38
     */
    public static String getClosedKeyword() {
        return closedKeyword;
    }

    /**
     * 打印并记录异常信息
     * @param info
     */
    private static void printAndLogError(String info) {
        System.err.println(info);
        log.error(info);
    }

}
