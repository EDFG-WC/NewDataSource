package com.laowang.datasource.javaconcurrency.msb.juc.c_020;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T10_TestReadWriteLock {
    static Lock lock = new ReentrantLock();
    private static int value;

    /**
     * 读写锁套餐: 分别获取读锁和写锁
     */
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over!");
            // 模拟读取操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v) {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("write over!");
            // 模拟写操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void read() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
//        // Runnable readR = ()-> read(lock);
//        Runnable readR = () -> read(readLock);
//
//        // Runnable writeR = ()->write(lock, new Random().nextInt());
//        Runnable writeR = () -> write(writeLock, new Random().nextInt());
//
//        for (int i = 0; i < 2; i++)
//            new Thread(writeR).start();
//        for (int i = 0; i < 18; i++)
//            new Thread(readR).start();

        new Thread(T10_TestReadWriteLock::read).start();
        new Thread(T10_TestReadWriteLock::read).start();
    }
}
