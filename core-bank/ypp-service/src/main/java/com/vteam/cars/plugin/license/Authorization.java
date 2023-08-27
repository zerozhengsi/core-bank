/**
 * 创建于: 2017年6月30日 下午4:12:12<br>
 * 所属项目:VTeam Framework
 * 文件名称:Authorization.java
 * 作者:oscar.yu
 * 版权信息:Copyright (c) 2001~2012, VTeam All Rights Reserved.
 */
package com.vteam.cars.plugin.license;

import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.plugin.license.crypto.Aes;
import com.vteam.cars.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 类功能描述:授权码处理对象
 *
 * @author oscar.yu
 * @version 1.0.0
 * @className Authorization.java
 * @history 2017年6月30日 oscar.yu 创建 Authorization.java
 */
@Slf4j
@Configuration
@Order(1)
public class Authorization {

    /**
     * 系统已许可授权标识
     */
    public static boolean HAS_AUTHORIZED = false;

    /**
     * 授权码文件路径
     */
    public static final String AUTHORIZATION_PATH = "license/Application.key";

    /**
     * 认证码信息
     */
    private static String serverid = StringUtils.EMPTY;

    /**
     * 授权码信息
     */
    private static String authorizationCode = StringUtils.EMPTY;

    /*
      读取授权码文件
     */
    static {
        // 验证及读取许可证
        License.init();
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = contextClassLoader.getResourceAsStream(AUTHORIZATION_PATH);
            if (null != input) {
                List<String> lines = IOUtils.readLines(input, LicenseUtils.UTF_8);
                if (CollectionUtils.isNotEmpty(lines) && lines.size() >= 2) {
                    serverid = lines.get(0);
                    authorizationCode = lines.get(1);
                }
            }
            if (StringUtils.isBlank(serverid) || StringUtils.isBlank(authorizationCode)) {
                printAndLogError("授权失败：获取Application.key文件失败。");
                String mac = License.getLocalMac();
                printAndLogError("授权申请，认证码信息：" + License.getServiceId(License.buildMacWith16DeviceInfo(mac)));
                System.exit(0);
            }
        } catch (IOException err) {
            printAndLogError("授权失败：获取Application.key文件失败。");
            System.exit(0);
        }
        /*
          验证授权码文件
         */
        HAS_AUTHORIZED = checkAuthorization();
    }

    /**
     * 验证授权码文件
     *
     * @return
     */
    private static boolean checkAuthorization() {
        // 使用配置文件判断是否需要临时屏蔽许可证认证控制
        String closedKeystr = License.getClosedKeystr();
        String closedKeyword = License.getClosedKeyword();
        boolean flag = false;
        if (StringUtils.isNotBlank(closedKeystr) && StringUtils.isNotBlank(closedKeyword) && closedKeystr.equals(closedKeyword)) {
            return !flag;
        }
        if (StringUtils.isBlank(serverid) || StringUtils.isBlank(authorizationCode)) {
            printAndLogError("授权失败：Application.key文件不存在。");
            System.exit(0);
            return flag;
        } else if (!Authorization.checkServerid(serverid)) {
            printAndLogError("授权失败：认证码不正确。");
            System.exit(0);
            return flag;
        } else if (!Authorization.checkLicense(serverid, authorizationCode)) {
            printAndLogError("授权失败：授权码不正确。");
            System.exit(0);
            return flag;
        }
        Date expireDate = License.getLicenseExpireDate();
        Date nowDate = new Date();
        if (null == expireDate || nowDate.after(nowDate)) {
            printAndLogError("授权失败：产品已到期，请重新购买授权，过期日期：" + new SimpleDateFormat(GlobalConstants.DateFormat.TO_DATE).format(expireDate) + "。");
            System.exit(0);
            return flag;
        }
        printAndLogError("授权成功：授权码正确。");
        return !flag;
    }

    /**
     * 验证授权码
     *
     * @param serverid serverid
     * @param license license
     * @return 验证结果
     */
    public static boolean checkLicense(String serverid, String license) {
        // 1.获取授权码参数信息
        // 机器物理地址
        String newMac = License.buildMacWithDeviceInfo(License.getLocalMac());
        // 产品名称
        String productName = License.getLicenseProductName();
        // 许可证代号
        String licenseid = License.getLicenseid();
        // 2.生成授权码KEY
        String authorizationKey = Authorization.getAuthorizationKey(newMac);
        // 3.构建授权码内容
        String authorizationCodeOriginal = serverid + LicenseUtils.SMME_EMAIL + productName + licenseid;
        String authorizationCode = StringUtils.EMPTY;
        // 生成授权码用于验证
        try {
            authorizationCode = Aes.encryptAsString(authorizationCodeOriginal, authorizationKey);
        } catch (Exception err) {
        }
        return authorizationCode.equals(license);
    }

    /**
     * 验证认证码
     *
     * @param serverCode serverCode
     * @return 验证结果
     */
    private static boolean checkServerid(String serverCode) {
        String newMac = License.buildMacWith16DeviceInfo(License.getLocalMac());
        String serverid = License.getServiceId(newMac);
        return serverid.equals(serverCode);
    }

    /**
     * 获取授权码KEY
     *
     * @param macAddress MAC地址
     */
    private static String getAuthorizationKey(String macAddress) {
        return LicenseUtils.SMME + macAddress.replaceAll(LicenseUtils.KEY_SEPARATOR, StringUtils.EMPTY);
    }

    /**
     * 打印并记录异常信息
     *
     * @param info info
     */
    private static void printAndLogError(String info) {
        System.err.println(info);
        log.error(info);
    }

}
