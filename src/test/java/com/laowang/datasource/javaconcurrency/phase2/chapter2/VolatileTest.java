package com.laowang.datasource.javaconcurrency.phase2.chapter2;

public class VolatileTest {

  private static final int MAX_VALUE = 5;
  private volatile static int INIT_VALUE = 0;

  public static void main(String[] args) {
    //如果INIT_VALUE喝localValue不相等就赋值一次
    new Thread(() -> {
      int localValue = INIT_VALUE;
      while (localValue < MAX_VALUE) {
        if (localValue != INIT_VALUE) {
          System.out.printf("The value updated to [%d]\n", INIT_VALUE);
          localValue = INIT_VALUE;
        }
      }
    }, "READER").start();
    // 小于MAX_VALUE就自增一次
    new Thread(() -> {
      int localValue = INIT_VALUE;
      while (INIT_VALUE < MAX_VALUE) {
        System.out.printf("Update the value to [%d]\n", ++localValue);
        INIT_VALUE = localValue;
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "UPDATER").start();
  }
}
