package com.vteam.vtarm.data;

/**
 * 可提供数据的接口 .<br>
 *
 * @author andy.sher
 * @date 2019/5/17 15:59
 */
public interface Provider {

    /**
     * 提供数据的方法 .
     *
     * @param
     * @return java.lang.String 此方法用于提供数据
     * @author andy.sher
     * @date 2019/5/17 16:18
     */
    String get(String... key);

    /**
     * 生成数据的方法，无数据时将会调用此方法进行数据生成 .
     *
     * @param
     * @return void
     * @author andy.sher
     * @date 2019/5/17 16:18
     */
    void build();

    /**
     * 当缓存中没有此信息时是否需要重构 .
     *
     * @param
     * @return boolean
     * @author fu.tong
     * @date 2019/6/27 13:30
     */
    boolean needRebuild();

}
