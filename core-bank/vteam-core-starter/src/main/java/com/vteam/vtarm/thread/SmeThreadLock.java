package com.vteam.vtarm.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 主线程锁（多线程同步时使用） .<br>
 *
 * @author andy.sher
 * @date 2019/1/7 10:23
 */
@Slf4j
public class SmeThreadLock {

    /**
     * 默认子线程数量
     */
    private static int DEFAULT_THREAD_COUNT = 1;

    /**
     * 计数器工具
     */
    private CountDownLatch countDownLatch;

    private SmeThreadLock(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    /**
     * 实例化一个默认子线程数量为1的线程锁 .
     *
     * @param
     * @return com.vteam.sme.common.SmeThreadLock
     * @author andy.sher
     * @date 2019/3/4 10:32
     */
    public static SmeThreadLock getInstance() {
        CountDownLatch countDownLatch = new CountDownLatch(DEFAULT_THREAD_COUNT);
        return new SmeThreadLock(countDownLatch);
    }

    /**
     * 实例化一个线程锁 .
     *
     * @param threadCount 子线程数量
     * @return com.vteam.sme.common.SmeThreadLock
     * @author andy.sher
     * @date 2019/3/4 10:32
     */
    public static SmeThreadLock getInstance(int threadCount) {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        return new SmeThreadLock(countDownLatch);
    }

    /**
     * 解锁 .
     *
     * @param
     * @return void
     * @author andy.sher
     * @date 2019/1/7 10:19
     */
    public void unlock() {
        countDownLatch.countDown();
    }

    /**
     * 锁定 .
     *
     * @param
     * @return void
     * @author andy.sher
     * @date 2019/1/7 10:10
     */
    public void lock() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
        }
    }

}
