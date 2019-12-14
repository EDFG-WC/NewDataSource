package com.laowang.datasource.javaconcurrency.phase2.chapter10;

public class ThreadLocalSimpleTest {

  private static ThreadLocal<String> threadLocal = new ThreadLocal(){
    @Override
    protected Object initialValue() {
      return "Alex";
    }
  };

  // JVM will start the main thread
  public static void main(String[] args) throws InterruptedException {
//    threadLocal.set("Alex");
    Thread.sleep(1_000);
    String value = threadLocal.get();
    System.out.println(value);
  }
}
