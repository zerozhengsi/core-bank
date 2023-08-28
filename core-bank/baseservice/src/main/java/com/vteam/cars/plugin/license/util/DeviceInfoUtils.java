/**
 * 创建于: 2018年9月4日 下午5:00:22<br>
 * 所属项目:VTeam Framework
 * 文件名称:DeviceInfoUtils.java
 * 作者:oscar.yu
 * 版权信息:Copyright (c) 2001~2018, VTeam All Rights Reserved.
 */
package com.vteam.cars.plugin.license.util;

import oshi.SystemInfo;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

import java.util.ArrayList;
import java.util.List;

/**
 * 类功能描述:设备信息工具类
 * @className DeviceInfoUtils.java
 *
 * @author oscar.yu
 * @version 1.0.0
 *
 * @history 2018年9月4日 oscar.yu 创建 DeviceInfoUtils.java
 */
public class DeviceInfoUtils {

    /**
     * 获取处理器序列号
     * @return
     */
    public static String generateProcessorSerialNumber() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        ComputerSystem computerSystem = hardwareAbstractionLayer.getComputerSystem();
        return computerSystem.getSerialNumber();
    }

    /**
     * 获取硬盘序列号
     */
    public static List<String> generateHardwareSerialNumbers() {
        List<String> hardwareSerialNumbers = new ArrayList<>();
        SystemInfo systemInfo = new SystemInfo();

        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();

        HWDiskStore[] stores = hardwareAbstractionLayer.getDiskStores();
        for (HWDiskStore store : stores) {
            String diskSerial = store.getSerial();
            hardwareSerialNumbers.add(diskSerial.trim());
        }
        return hardwareSerialNumbers;
    }

    /**
     * 获取Mac地址
     */
    public static List<String> generateMacAddress() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();

        NetworkIF[] nifs = hardwareAbstractionLayer.getNetworkIFs();
        List<String> list = new ArrayList<>();
        for (NetworkIF nif : nifs) {
            if ((nif.getIPv4addr() != null) && (nif.getIPv4addr().length > 0)) {
                list.add(nif.getMacaddr());
            }
        }
        return list;
    }
}
