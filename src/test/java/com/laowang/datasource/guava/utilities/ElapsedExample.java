package com.laowang.datasource.guava.utilities;

import java.util.concurrent.TimeUnit;

public class ElapsedExample {

  // 模拟一个受理业务并打印log的功能
  public static void main(String[] args) throws InterruptedException {
    process("3463542353");
  }

  private static void process(String orderNum) throws InterruptedException {
    System.out.printf("Start process the order Num %s\n", orderNum);
    long startTime = System.nanoTime();
    TimeUnit.SECONDS.sleep(1);
    System.out.printf("The orderNo %s process successful and elapsed %d ns.\n", orderNum,
        (System.nanoTime() - startTime));

  }

}
