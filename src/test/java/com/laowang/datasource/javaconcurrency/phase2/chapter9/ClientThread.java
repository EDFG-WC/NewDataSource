package com.laowang.datasource.javaconcurrency.phase2.chapter9;

import java.util.Random;

public class ClientThread extends Thread {

  private final RequestQueue queue;
  private final Random random;
  private final String sendValue;

  public ClientThread(RequestQueue queue, String sendValue) {
    this.queue = queue;
    this.sendValue = sendValue;
    random = new Random(System.currentTimeMillis());
  }

  @Override
  public void run() {
    for (int index = 0; index < 10; index++) {
      System.out.println("Client -> request " + sendValue);
      queue.putRequest(new Request(sendValue));
      try {
        Thread.sleep(random.nextInt(1000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
