package com.laowang.datasource.JavaConcurrency.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class BooleanLock implements Lock {
    // false indicates the lock has been acquired.
    // true indicates the lock is still available.
    private boolean initValue;

    private Thread currentThread;

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    /**
     * 构造方法
     */
    public BooleanLock() {
        this.initValue = false;
    }

    /**
     * 用lock方法来拿锁
     *
     * @throws InterruptedException
     */
    @Override
    public synchronized void lock() throws InterruptedException {
        // 如果是true: 代码就会一直在这个while循环里, 不会执行下面的部分.
        while (initValue) {
            blockedThreadCollection.add(Thread.currentThread());
            System.out.println("Thread " + Thread.currentThread().getName() + " waits.");
            this.wait();
        }
        // 如果是false:
        this.initValue = true;
        System.out.println("Thread " + Thread.currentThread().getName() + " turned init value into true.");
        // 如果是true的情况下, 当前线程不应该在这个集合中
        blockedThreadCollection.remove(Thread.currentThread());
        System.out.println("Thread " + Thread.currentThread().getName() + " removed from the list.");
    }

    /**
     * 当某个线程抢到锁, 而超过time没有释放, 其他试图抢锁的线程就放弃
     *
     * @param time
     * @throws InterruptedException
     * @throws TimeOutExeception
     */
    @Override
    public synchronized void lock(long time) throws InterruptedException, TimeOutExeception {
        if (time <= 0) {
            lock();
        }
        // 这个时间如果小于0, 就抛出TimeOutExeception
        long timeOutFlag = time;
        // 理论上的结束时间
        long endTime = System.currentTimeMillis() + time;
        while (initValue) {
            if (timeOutFlag <= 0) {
                throw new TimeOutExeception("Time out.");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(time);
            //每次都会计算这个时间, 结束时间一旦比当前时间小, 其他线程就不用等待了.
            timeOutFlag = endTime - System.currentTimeMillis();
        }
        this.initValue = true;
        this.currentThread = Thread.currentThread();
        // 如果是true的情况下, 当前线程不应该在这个集合中
        blockedThreadCollection.remove(Thread.currentThread());
    }

    /**
     * 使用完之后, 释放锁
     */
    @Override
    public synchronized void unlock() {
        if (Thread.currentThread() == currentThread) {
            // 先把初始值置为false
            this.initValue = false;
            // 告诉其他所有线程 这个锁可以用了
            this.notifyAll();
            Optional.of("Thread " + Thread.currentThread().getName() + " released the lock monitor.").ifPresent(System.out::println);
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        // 不让别人修改这个集合, 一修改就会报错.
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
