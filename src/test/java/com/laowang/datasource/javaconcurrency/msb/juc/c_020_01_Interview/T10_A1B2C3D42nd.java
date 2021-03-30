package com.laowang.datasource.javaconcurrency.msb.juc.c_020_01_Interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T10_A1B2C3D42nd {
    private static int size = 26;

    private static Lock lock = new ReentrantLock();
    private static Condition charCon = lock.newCondition();
    private static Condition numCon = lock.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < size; i++) {
                try {
                    lock.lock();
                    System.out.print((char)(65 + i));
                    numCon.signalAll();
                    charCon.await();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }, "charThread").start();

        new Thread(() -> {
            for (int i = 0; i < size; i++) {
                try {
                    lock.lock();
                    System.out.print(i + 1);
                    charCon.signalAll();
                    numCon.await();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }, "numThread").start();

    }
}
