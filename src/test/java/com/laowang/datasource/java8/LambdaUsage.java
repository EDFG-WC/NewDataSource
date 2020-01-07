package com.laowang.datasource.java8;

public class LambdaUsage {

  public static void main(String[] args) {
    Runnable runnable1 = () -> System.out.println("Hello1");
    Runnable runnable2 = new Runnable() {
      @Override
      public void run() {
        System.out.println("Hello2");
      }
    };
    process(runnable1);
    process(runnable2);
    process(()-> System.out.println("Hello3"));
  }

  private static void process(Runnable r) {
    r.run();
  }
}

