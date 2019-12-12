package com.laowang.datasource.javaconcurrency.phase2.chapter9;

import java.util.Random;

public class ServerThread extends Thread {

  private final RequestQueue queue;
  private final Random random;

  public ServerThread(RequestQueue queue) {
    this.queue = queue;
    this.random = new Random(System.currentTimeMillis());
  }

  private volatile boolean flag = true;

  @Override
  public void run() {
    while (flag) {
      Request request = queue.getRequest();
      // 2. queue为空的时候, queue就会启动wait方法, 如果被打断就会返回空, 这里忽略这种情况
      if (null == request) {
        System.out.println("Received a null request.");
        continue;
      }
      System.out.println("Sever -> " + request.getValue());
      try {
        // 3. sleep的时候也可以被打断, 返回到方法的调用位置
        Thread.sleep(random.nextInt(1_000));
      } catch (InterruptedException e) {
        return;
      }
      // 1. 执行到这里, flag被设置为true, 代码中断
    }
  }

  public void close() {
    this.flag = false;
    // 防止queue执行了wait()方法之后, 置false也不会有效果. 如果我打断, 就会立即让那边break.
    this.interrupt();
  }
}
