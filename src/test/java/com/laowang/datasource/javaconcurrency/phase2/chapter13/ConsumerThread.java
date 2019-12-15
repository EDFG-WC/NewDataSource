package com.laowang.datasource.javaconcurrency.phase2.chapter13;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsumerThread extends Thread {

  private final MessageQueue messageQueue;

  private final static Random random = new Random(System.currentTimeMillis());

  private final static AtomicInteger counter = new AtomicInteger(0);

  public ConsumerThread(
      MessageQueue messageQueue, int seq) {
    super("CONSUMER-" + seq);
    this.messageQueue = messageQueue;
  }

  @Override
  public void run() {
    while (true) {
      try {
        Message message = messageQueue.take();
        System.out.println(Thread.currentThread().getName() + " takes a message " + message);
        Thread.sleep(random.nextInt(1_000));
      } catch (InterruptedException e) {
        break;
      }
    }
  }
}
