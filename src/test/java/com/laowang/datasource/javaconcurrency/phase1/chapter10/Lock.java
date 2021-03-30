package com.laowang.datasource.javaconcurrency.phase1.chapter10;

import java.util.Collection;

public interface Lock {
    // 自定义异常类
    public static class TimeOutException extends Exception {
        public TimeOutException(String message) {
            super(message);
        }
    }

    // 锁线程的方法
    void lock() throws InterruptedException;

    // 在指定时间内之内还没获取到锁就退出
    void lock(long time) throws InterruptedException, TimeOutException;

    // 释放锁
    void unlock();

    // 如果指定上锁时间就用这个
    void unlockInTime();

    Collection<Thread> getBlockedThread();

    int getBlockedSize();
}
