package com.laowang.datasource.javaconcurrency.phase2.chapter16;

import java.util.Random;

public class CounterIncrement extends Thread {

  private volatile boolean terminated = false;
  private int counter = 0;
  private Random random = new Random(System.currentTimeMillis());

  @Override
  public void run() {
    try {
      while (!terminated) {
        System.out.println(Thread.currentThread().getName() + " " + counter++);
        Thread.sleep(random.nextInt(1_000));
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      this.clean();
    }
  }

  private void clean() {
    System.out.println("Do some clean work for the second phase. current counter: " + counter);
  }

  public void close() {
    this.terminated = true;
    this.interrupt();
  }
}
