package com.laowang.datasource.javaconcurrency.phase2.chapter15;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MessageHandler {

  private static final Random random = new Random(System.currentTimeMillis());

  private final static Executor executor = Executors.newFixedThreadPool(5);

  public void request(Message message) {
    executor.execute(() -> {
      String value = message.getValue();
      try {
        Thread.sleep(random.nextInt(1_000));
        System.out.println(
            "The message will be handled by " + Thread.currentThread().getName() + " " + value);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
//    new Thread(() -> {
//      String value = message.getValue();
//      try {
//        Thread.sleep(random.nextInt(1_000));
//        System.out.println("The message will be handled by " + Thread.currentThread().getName());
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    }).start();
  }
}
