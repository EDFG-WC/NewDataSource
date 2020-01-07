package com.laowang.datasource.javaconcurrency.phase2.chapter15;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler {

  private static final Random random = new Random(System.currentTimeMillis());

  private static final Executor executor = Executors.newFixedThreadPool(5);
  public void request(Message message) {
    new Thread(() -> {
      String value = message.getValue();
      try {
        Thread.sleep(random.nextInt(1_000));
        System.out.println("The message will be handled by " + Thread.currentThread().getName());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }

  // 在我们的测试例子中, 如果按照一开始的模式, 我们要创建10个线程, 但线程池就可以循环利用里面的5个线程
  public void betterRequest(Message message) {
    executor.execute(() -> {
      String value = message.getValue();
      try {
        Thread.sleep(random.nextInt(1_000));
        System.out.println("The message will be handled by " + Thread.currentThread().getName());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }

  public void shutdown() {
    ((ExecutorService) executor).shutdown();
  }
}
