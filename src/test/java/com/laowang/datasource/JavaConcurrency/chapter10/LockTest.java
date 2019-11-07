package com.laowang.datasource.JavaConcurrency.chapter10;

import java.util.Optional;
import java.util.stream.Stream;

public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1", "T2", "T3", "T4").forEach(name -> new Thread(() -> {
            try {
                booleanLock.lock();
                Optional.of(Thread.currentThread().getName() + " now has the lock monitor.").ifPresent(System.out::println);
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                booleanLock.unlock();
            }
        }, name).start());
        Thread.sleep(1_000);
        booleanLock.unlock();
    }

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + " is working...").ifPresent(System.out::println);
        Thread.sleep(10_000);
    }
}
