package com.vteam.vtarm.codec;

import jakarta.annotation.Resource;

/**
 * 数据处理调度器 .<br>
 *
 * @author andy.sher
 * @date 2018/7/12 15:45
 */
public class DataCodecExecutor {

    @Resource
    private DataCodecStrategyGroup dataCodecStrategyGroup;

    /**
     * 执行数据解密处理 .<br>
     *
     * @param target 数据对象
     * @return void
     * @author andy.sher
     * @date 2018/7/12 16:36
     */
    public void decrypt(Object target) {
        if (null == dataCodecStrategyGroup.getDataCodecStrategyMap() || dataCodecStrategyGroup.getDataCodecStrategyMap().isEmpty()) {
            return;
        }
        if (null != target) {
            if (dataCodecStrategyGroup.getDataCodecStrategyMap().containsKey(target.getClass().getSimpleName())) {
                dataCodecStrategyGroup.getDataCodecStrategyMap().get(target.getClass().getSimpleName()).doDecrypt(target);
            } else if (dataCodecStrategyGroup.getDataCodecStrategyMap().containsKey(target.getClass().getSuperclass().getSimpleName())) {
                dataCodecStrategyGroup.getDataCodecStrategyMap().get(target.getClass().getSuperclass().getSimpleName()).doDecrypt(target);
            }
        }
    }

    /**
     * 执行数据加密处理 .<br>
     *
     * @param target 数据对象
     * @return void
     * @author andy.sher
     * @date 2018/7/12 16:36
     */
    public void encrypt(Object target) {
        if (null == dataCodecStrategyGroup.getDataCodecStrategyMap() || dataCodecStrategyGroup.getDataCodecStrategyMap().isEmpty()) {
            return;
        }
        if (null != target) {
            if (dataCodecStrategyGroup.getDataCodecStrategyMap().containsKey(target.getClass().getSimpleName())) {
                dataCodecStrategyGroup.getDataCodecStrategyMap().get(target.getClass().getSimpleName()).doEncrypt(target);
            } else if (dataCodecStrategyGroup.getDataCodecStrategyMap().containsKey(target.getClass().getSuperclass().getSimpleName())) {
                dataCodecStrategyGroup.getDataCodecStrategyMap().get(target.getClass().getSuperclass().getSimpleName()).doEncrypt(target);
            }
        }
    }

}
