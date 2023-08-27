package com.vteam.vtarm.task;

import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 定时任务 .<br>
 *
 * @author andy.sher
 * @date 2019/3/26 10:01
 */
@Slf4j
public abstract class Task implements Runnable {

    /**
     * 是否执行 .
     *
     * @param
     * @return boolean 是否执行
     * @author andy.sher
     * @date 2019/12/2 12:50
     */
    protected abstract boolean active();

    /**
     * 要做的事情 .
     *
     * @param
     * @return int 业务笔数，用于日志记录（值为null或-1时不记录笔数）
     * @author andy.sher
     * @date 2019/12/2 14:21
     */
    public abstract Integer execute() throws IOException;

    @Override
    public void run() {
        if (this.active()) {
            try {
                Task proxy = SpringContextUtils.getBean(this.getClass());
                proxy.execute();
            } catch (IOException e) {
                log.error("定时任务执行失败。", e);
            }
        }
    }
}
