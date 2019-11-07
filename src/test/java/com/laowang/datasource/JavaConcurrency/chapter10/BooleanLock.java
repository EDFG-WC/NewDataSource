package com.laowang.datasource.JavaConcurrency.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class BooleanLock implements Lock {
    // false indicates the lock has been acquired.
    // true indicates the lock was still available.
    private boolean initValue;

    private Thread currentThread;

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        blockedThreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeOutExeception {

    }

    @Override
    public synchronized void unlock() {
        this.initValue = false;
        Optional.of(Thread.currentThread() + " released the lock monitor.").ifPresent(System.out::println);
        this.notifyAll();
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        // 这里要注意 不能给别的方法修改这个集合的机会
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
