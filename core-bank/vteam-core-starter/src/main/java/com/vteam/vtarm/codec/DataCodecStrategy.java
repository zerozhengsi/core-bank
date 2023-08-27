package com.vteam.vtarm.codec;

/**
 * 数据处理策略 .<br>
 *
 * @author andy.sher
 * @date 2018/7/12 15:45
 */
public interface DataCodecStrategy {

    /**
     * 策略ID .
     *
     * @param
     * @return java.lang.String
     * @author andy.sher
     * @date 2018/8/13 17:52
     */
    Class<?> getId();

    /**
     * 字段数组 .
     *
     * @param
     * @return java.lang.String[]
     * @author andy.sher
     * @date 2018/10/9 13:21
     */
    String[] getFields();

    /**
     * 执行解密处理 .<br>
     *
     * @param target 处理对象
     * @return void
     * @author andy.sher
     * @date 2018/7/12 9:18
     */
    void doDecrypt(Object target);

    /**
     * 执行加密处理 .<br>
     *
     * @param target 处理对象
     * @return void
     * @author andy.sher
     * @date 2018/7/12 9:18
     */
    void doEncrypt(Object target);

}
