package com.laowang.datasource.javaconcurrency.phase2.chapter12;

import java.io.IOException;

public class AutosaverThread extends Thread {

  private BalkingData balkingData;

  public AutosaverThread(String name,
      BalkingData balkingData) {
    super(name);
    this.balkingData = balkingData;
  }

  @Override
  public void run() {
    try {
      while (true) {
        balkingData.save();
        Thread.sleep(1_000L);
      }
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }
}
