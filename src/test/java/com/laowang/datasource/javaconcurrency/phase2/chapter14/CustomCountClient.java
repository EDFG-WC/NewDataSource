package com.laowang.datasource.javaconcurrency.phase2.chapter14;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CustomCountClient {

  public static void main(String[] args) throws InterruptedException {
    final CountDown latch = new CountDown(5);
    System.out.println("准备多线程处理任务.");
    // 1st phase
    IntStream.rangeClosed(1, 5).forEach(i -> new Thread(() -> {
      System.out.println(Thread.currentThread().getName()+ " is working.");
      try {
        Thread.sleep(1_000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      latch.down();
    }, String.valueOf(i)).start());
    // 2nd phase
    latch.await();
    System.out.println("多线程任务全部结束, 准备第二阶段任务.");
    System.out.println(".....");
    System.out.println("FINISH.");
  }
}
