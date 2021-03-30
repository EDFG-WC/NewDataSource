package com.laowang.datasource.javaconcurrency.phase1.chapter10;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        secondVersion();
    }

    /**
     * 模拟业务代码
     * 
     * @throws InterruptedException
     */
    private static void work() throws InterruptedException {
        Optional.of("Thread " + Thread.currentThread().getName() + " is working...").ifPresent(System.out::println);
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * 我们可以看到 当4个线程同时吊起执行任务的时候 抢到锁的
     * 
     * @throws InterruptedException
     */
    private static void firstVersion() throws InterruptedException {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1", "T2", "T3", "T4").forEach(name -> new Thread(() -> {
            try {
                booleanLock.lock();
                Optional.of("Thread " + Thread.currentThread().getName() + " now has the lock monitor.")
                    .ifPresent(System.out::println);
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                booleanLock.unlock();
            }
        }, name).start());
        // bug点: 如果有人乱用这个API, 在逻辑之外调用unlock打断了当前线程怎么办?
        /*Thread.sleep(1_000);
        booleanLock.unlock();*/
    }

    private static void secondVersion() {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1", "T2", "T3", "T4").forEach(name -> new Thread(() -> {
            try {
                booleanLock.lock(100L);
                Optional.of("Thread " + Thread.currentThread().getName() + " now has the lock monitor.")
                    .ifPresent(System.out::println);
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Lock.TimeOutException timeOutException) {
                Optional.of(Thread.currentThread().getName() + " time out.").ifPresent(System.out::println);
            } finally {
                booleanLock.unlockInTime();
            }
        }, name).start());
    }
}
