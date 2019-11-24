package com.laowang.datasource.javaconcurrency.phase2.chapter2;

import java.util.Optional;
import java.util.stream.IntStream;

public class WaitSet {

  private static final Object LOCK = new Object();

  /**
   * 1.所有的对象都有一个wait set, 用来存放调用了该对象wait方法只会进入block状态线程. 2.线程被notify之后, 不一定立即执行. 3.线程从wait
   * set中唤醒的顺序不一定是FIFO. 4.线程被唤醒后, 必须重新唤醒锁.
   *
   * @param args
   */
  public static void main(String[] args) throws InterruptedException {
    IntStream.rangeClosed(1, 10).forEach(i -> {
      new Thread(String.valueOf(i)) {
        @Override
        public void run() {
          synchronized (LOCK) {
            try {
              Optional.of(Thread.currentThread().getName() + " will enter the wait set")
                  .ifPresent(System.out::println);
              LOCK.wait();
              Optional.of(Thread.currentThread().getName() + " will leave the wait set")
                  .ifPresent(System.out::println);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }.start();
    });

    try {
      Thread.sleep(3_000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    IntStream.rangeClosed(1, 10).forEach(i -> {
      synchronized (LOCK) {
        LOCK.notify();
        try {
          Thread.sleep(1_000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
