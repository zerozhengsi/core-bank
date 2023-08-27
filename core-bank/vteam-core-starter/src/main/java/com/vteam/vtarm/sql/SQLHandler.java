package com.vteam.vtarm.sql;

/**
 * 处理器 .<br>
 *
 * @author andy.sher
 * @date 2018/7/12 9:17
 */
public interface SQLHandler extends Comparable<SQLHandler> {

    /**
     * 初始化操作 .<br>
     *
     * @return void
     * @author andy.sher
     * @date 2018/7/12 9:18
     */
    void init();

    /**
     * 执行处理器链 .<br>
     *
     * @param model sql模型
     * @return void
     * @author andy.sher
     * @date 2018/7/12 9:18
     */
    void doHandler(SQLModel model);

    /**
     * 获取下一个处理器对象 .<br>
     *
     * @return com.vteam.sme.plugin.sqladapt.support.SQLHandler 处理器
     * @author andy.sher
     * @date 2018/7/12 9:18
     */
    SQLHandler getNextHandler();

    /**
     * 设置下一个处理器对象 .<br>
     *
     * @param nextHandler 下一个处理器
     * @return void
     * @author andy.sher
     * @date 2018/7/12 9:19
     */
    void setNextHandler(SQLHandler nextHandler);

    /**
     * 获取优先级 .<br>
     *
     * @return int 优先级
     * @author andy.sher
     * @date 2018/7/12 9:19
     */
    int getPriority();

    /**
     * 设置权重，数字越大，优先级越高 .<br>
     *
     * @param weight 权重
     * @return void
     * @author andy.sher
     * @date 2018/7/12 9:20
     */
    void setPriority(int weight);

    /**
     * 是否还有下一个处理器 .<br>
     *
     * @return boolean 是否还有下一个处理器
     * @author andy.sher
     * @date 2018/7/12 9:21
     */
    boolean hasNext();

    /**
     * 销毁 .<br>
     *
     * @return void
     * @author andy.sher
     * @date 2018/7/12 9:21
     */
    void destroy();

}
