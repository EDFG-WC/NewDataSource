package com.laowang.datasource.javaconcurrency.phase2.chapter2;

public class VolatileTest2 {

  private static final int MAX_VALUE = 500;
  private static volatile int INIT_VALUE = 0;

  public static void main(String[] args) {
    //如果INIT_VALUE喝localValue不相等就赋值一次
    new Thread(() -> {
      while (INIT_VALUE < MAX_VALUE) {
        System.out.println("ADDER-1-> " + (++INIT_VALUE));
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "ADDER-1").start();
    // 小于MAX_VALUE就自增一次
    new Thread(() -> {
      while (INIT_VALUE < MAX_VALUE) {
        while (INIT_VALUE < MAX_VALUE) {
          System.out.println("ADDER-2-> " + (++INIT_VALUE));
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }, "ADDER-2").start();
  }
}
