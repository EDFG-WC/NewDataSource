package com.laowang.datasource.javaconcurrency.phase2.chapter6;

import java.util.Random;

/**
 * WriteWorker, 实际工作中 writeworker和readworker可能是一个类
 */
public class WriteWorker extends Thread {

  private static final Random random = new Random(System.currentTimeMillis());

  private final SharedData data;
  private final String filter;
  private int index = 0;

  public WriteWorker(SharedData data, String filter) {
    this.data = data;
    this.filter = filter;
  }

  @Override
  public void run() {
    try {
      //保证代码报错, 循环停止, try-catch要包含住while-true循环
      while (true) {
        char ch = nextChar();
        data.write(ch);
        Thread.sleep(random.nextInt(1000));
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * 获得
   * @return
   */
  private char nextChar() {
    char ch = filter.charAt(index);
    index++;
    if (index >= filter.length()) {
      index = 0;
    }
    return ch;
  }
}
