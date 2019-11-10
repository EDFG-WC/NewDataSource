package com.laowang.datasource.JavaConcurrency.chapter10;

import java.util.Optional;
import java.util.stream.Stream;

public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1", "T2", "T3", "T4").forEach(name -> new Thread(() -> {
            try {
                booleanLock.lock(100L);
                Optional.of("Thread " + Thread.currentThread().getName() + " now has the lock monitor.").ifPresent(System.out::println);
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Lock.TimeOutExeception timeOutExeception) {
                Optional.of(Thread.currentThread().getName() + " time out.").ifPresent(System.out::println);
            } finally {
                booleanLock.unlock();
            }
        }, name).start());
        //bug点:
        /*Thread.sleep(1_000);
        booleanLock.unlock();*/
    }

    private static void work() throws InterruptedException {
        Optional.of("Thread " + Thread.currentThread().getName() + " is working...").ifPresent(System.out::println);
        Thread.sleep(90);
    }
}
