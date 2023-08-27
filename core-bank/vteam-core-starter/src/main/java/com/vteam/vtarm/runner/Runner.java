package com.vteam.vtarm.runner;

/**
 * 启动项 .<br>
 *
 * @author andy.sher
 * @date 2019/11/21 17:39
 */
public interface Runner {

    /**
     * 执行顺序 .
     *
     * @param
     * @return int 值越小，优先级越高；值越大，优先级越低
     * @author andy.sher
     * @date 2019/11/21 17:42
     */
    int order();

    /**
     * 执行内容 .
     *
     * @param
     * @return void
     * @author andy.sher
     * @date 2019/11/21 17:43
     */
    void run() throws Exception;

}
