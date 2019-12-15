package com.laowang.datasource.javaconcurrency.phase2.chapter12;

import java.io.IOException;
import java.util.Random;

public class ManualsaverThread extends Thread {

  private BalkingData balkingData;
  private Random random = new Random(System.currentTimeMillis());

  public ManualsaverThread(String name,
      BalkingData balkingData) {
    super(name);
    this.balkingData = balkingData;
  }

  @Override
  public void run() {
    for (int index = 0; index < 21; index++) {
      try {
        balkingData.change("he is fang " + index);
        Thread.sleep(random.nextInt(1000));
        balkingData.save();
      } catch (InterruptedException | IOException e) {
        e.printStackTrace();
      }
    }
  }
}
