package com.laowang.datasource.javaconcurrency.phase2.chapter8;

public class SyncInvoker {

  public static void main(String[] args) throws InterruptedException {
    FutureService futureService = new FutureService();
    /**
     * 这里通过一个lambda表达式作为submit方法的参数, 它描述了我们要做的事情, 此处的lambda表达式实际上是实现了FutureTask的一个匿名内部类, 它和MyFutureTask完全等价
     */
    MyFutureTask myFutureTask = new MyFutureTask();
    Future<String> future = futureService.submit(() -> {
      try {
        Thread.sleep(10_001);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "FINISHED";
    }, System.out::println);
    System.out.println("================");
    System.out.println("Do some other stuff.");
    System.out.println("================");
  }
}

class MyFutureTask implements FutureTask {

  @Override
  public String call() {
    try {
      Thread.sleep(10_001);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "FINISH";
  }
}

