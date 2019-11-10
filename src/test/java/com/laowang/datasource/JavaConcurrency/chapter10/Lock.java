package com.laowang.datasource.JavaConcurrency.chapter10;

        import java.util.Collection;

public interface Lock {
    // 自定义异常类
    public static class TimeOutExeception extends Exception {
        public TimeOutExeception(String message) {
            super(message);
        }
    }

    // 锁线程的方法
    void lock() throws InterruptedException;

    // 在指定时间内之内还没获取到锁就退出
    void lock(long time) throws InterruptedException, TimeOutExeception;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockedSize();
}
