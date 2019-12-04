package com.laowang.datasource.javaconcurrency.phase2.chapter2;

public class Question {

  private static final Object LOCK = new Object();

  /**
   * 疑惑: 调用wait方法之后, 线程进入了wait set, 同时释放了synchronized权限, 那么等到notify方法被调用, 再次竞争锁的时候, 是从哪里开始运行呢? 实际上不会从再次争抢到锁的位置运行, 地址恢复机制会让代码从wait的位置接着运行下去.
   */
  private static void work() {
    synchronized (LOCK) {
      System.out.println("Work begins.");
      try {
        System.out.println("Thread coming.");
        LOCK.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Thread leaving.");
    }
  }

  public static void main(String[] args) throws InterruptedException {
    new Thread(() -> {
      work();
    }).start();
    Thread.sleep(1_000);
    // notify和wait一样都需要锁来配合:
    synchronized (LOCK) {
      LOCK.notify();
    }
  }
}
