package com.vteam.vtarm.filter;

/**
 * 过滤器基类 .<br>
 *
 * @author andy.sher
 * @date 2019/11/19 9:15
 */
public interface Filter {

    /**
     * 最后执行
     */
    int ORDER_MIN = 99999;

    /**
     * 最先执行
     */
    int ORDER_MAX = 0;

    /**
     * 前置
     */
    int PRE = 1;

    /**
     * 环绕
     */
    int AROUND = 2;

    /**
     * 后置
     */
    int AFTER = 3;

    /**
     * 表示这个过滤器是否起作用 .
     *
     * @param
     * @return boolean true 为起作用，false为不起作用，不起作用的话将不会执行handle
     * @author andy.sher
     * @date 2019/11/19 9:27
     */
    boolean active();

    /**
     * 执行权重，值越小，优先级越高 .
     *
     * @param
     * @return int 数字值
     * @author andy.sher
     * @date 2019/11/19 9:28
     */
    int order();

    /**
     * 处理方法，将处理逻辑实现在这个方法里 .
     *
     * @param
     * @return void
     * @author andy.sher
     * @date 2019/11/19 9:29
     */
    void handle() throws RuntimeException;

    /**
     * 过滤器类型[1表示前置，2表示环绕，3表示后置] .
     *
     * @param
     * @return int
     * @author andy.sher
     * @date 2019/11/19 11:44
     */
    int type();

}
