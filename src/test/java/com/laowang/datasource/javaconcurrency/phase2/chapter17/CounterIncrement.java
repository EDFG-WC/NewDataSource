package com.laowang.datasource.javaconcurrency.phase2.chapter17;

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
        Thread.sleep(random.nextInt(1000));
      }
    } catch (InterruptedException e) {
//      e.printStackTrace();
    } finally { // 2 phases pattern的精髓就在这里: 创建对象之后调用close()方法打断线程, 然后执行finally代码块. 在代码块里可以做一些必要的工作.
      this.clean();
    }
  }

  public void close() {
    this.terminated = true;
    this.interrupt();
  }
  // 这个模式所谓的第二阶段就是这个clean
  public void clean() {
    System.out.println("do some clean work for second phase. current counter  :" + counter);
  }
}
