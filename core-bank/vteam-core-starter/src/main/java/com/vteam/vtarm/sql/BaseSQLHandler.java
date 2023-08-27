package com.vteam.vtarm.sql;

import com.vteam.vtarm.sql.SQLHandler;

/**
 * SQL处理器基类 .<br>
 *
 * @author andy.sher
 * @date 2018/7/12 9:24
 */
public abstract class BaseSQLHandler implements SQLHandler {

    /**
     * 下一个处理器对象
     */
    private SQLHandler nextHandler;

    /**
     * 优先级
     */
    private int priority;

    @Override
    public SQLHandler getNextHandler() {
        return nextHandler;
    }

    @Override
    public void setNextHandler(SQLHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean hasNext() {
        return null != this.nextHandler;
    }

    /**
     * 按优先级降序排序 .<br>
     *
     * @author andy.sher
     * @date 2018/7/12 10:04
     */
    @Override
    public int compareTo(SQLHandler handler) {
        int result = handler.getPriority() - this.getPriority();
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return 0;
        }
    }

}
