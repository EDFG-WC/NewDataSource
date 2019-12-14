package com.laowang.datasource.javaconcurrency.phase2.chapter10;

import java.util.Random;

public class ThreadLocalComplicatedTest {

  private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

  private static final Random random = new Random(System.currentTimeMillis());

  // JVM will start the main thread
  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(() -> {
      threadLocal.set("Thread-T1");
      try {
        Thread.sleep(random.nextInt(1000));
        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    Thread t2 = new Thread(() -> {
      threadLocal.set("Thread-T2");
      try {
        Thread.sleep(random.nextInt(1000));
        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println("==================================================");
    System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());

  }
}
