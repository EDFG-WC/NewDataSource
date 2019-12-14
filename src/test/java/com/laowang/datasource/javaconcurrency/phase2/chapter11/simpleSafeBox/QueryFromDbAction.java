package com.laowang.datasource.javaconcurrency.phase2.chapter11.simpleSafeBox;

public class QueryFromDbAction {

  public void execute(Context context) {
    try {
      Thread.sleep(1_000L);
      String name = "Alex "+ Thread.currentThread().getName();
      context.setName(name);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
