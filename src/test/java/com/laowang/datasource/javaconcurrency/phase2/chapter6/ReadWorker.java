package com.laowang.datasource.javaconcurrency.phase2.chapter6;

public class ReadWorker extends Thread {

  private final SharedData data;

  public ReadWorker(SharedData data) {
    this.data = data;
  }

  @Override
  public void run() {
    try {
      while (true) {
        char[] readBuff = data.read();
        System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readBuff));
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
