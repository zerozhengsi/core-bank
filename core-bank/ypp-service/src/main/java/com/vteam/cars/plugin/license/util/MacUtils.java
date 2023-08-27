/**
 * 创建于: 2017年6月30日 下午5:28:28<br>
 * 所属项目:VTeam Framework
 * 文件名称:MacUtils.java
 * 作者:oscar.yu
 * 版权信息:Copyright (c) 2001~2012, VTeam All Rights Reserved.
 */
package com.vteam.cars.plugin.license.util;

import com.vteam.cars.util.StringUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 类功能描述:硬件相关工具类
 * @className MacUtils.java
 *
 * @author oscar.yu
 * @version 1.0.0
 *
 * @history 2017年6月30日 oscar.yu 创建 MacUtils.java
 */
public class MacUtils {

    /**
     * docker网卡
     */
    private static final String NT_DOCKER = "docker";

    private static final String LOCAL_IP = "127.0.0.1";

    /**
     * 获取当前机器本地MAC地址列表
     * @return
     */
    public static List<String> getLocalMacStringList() {
        List<String> macs = new ArrayList<>();
        List<NetworkInterface> networkInterfaceList = getLocalNetworkInterface();
        try {
            if (CollectionUtils.isEmpty(networkInterfaceList)) {
                return macs;
            }
            for (NetworkInterface networkInterface : networkInterfaceList) {
                String name = networkInterface.getName();
                if (!name.contains(NT_DOCKER)) {
                    Enumeration<InetAddress> address = networkInterface.getInetAddresses();
                    InetAddress inetAddress = null;
                    while (address.hasMoreElements()) {
                        inetAddress = address.nextElement();
                        if ((inetAddress instanceof Inet4Address)
                                && (!LOCAL_IP.equals(inetAddress.getHostAddress()))) {
                            String mac = getLocalMacByNetworkInterface(inetAddress);
                            if (mac != null) {
                                macs.add(mac);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            // throw new UncheckedException("获取MAC地址时发生异常", e);
        }
        return macs;
    }

    /**
     * 获取网卡的Mac地址
     * @param inetAddress 网卡地址
     */
    public static String getLocalMacByNetworkInterface(InetAddress inetAddress) {
        StringBuffer sb = new StringBuffer(StringUtils.EMPTY);
        try {
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            if (mac == null) {
                return null;
            }
            if (mac.length > 6) {
                return null;
            }

            for (int i = 0; i < mac.length; ++i) {
                if (i != 0) {
                    sb.append("-");
                }

                int temp = mac[i] & 0xFF;
                String str = Integer.toHexString(temp);

                if (str.length() == 1)
                    sb.append("0" + str);
                else {
                    sb.append(str);
                }
            }
        } catch (Exception e) {
            // throw new UncheckedException("获取MAC地址时发生异常", e);
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 获取当前机器本地网卡列表
     */
    public static List<NetworkInterface> getLocalNetworkInterface() {
        List<NetworkInterface> networkInterfaceList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual()) {
                    continue;
                }
                networkInterfaceList.add(networkInterface);
            }
        } catch (Exception e) {
            // throw new UncheckedException("获取MAC地址时发生异常", e);
        }
        return networkInterfaceList;
    }

}
