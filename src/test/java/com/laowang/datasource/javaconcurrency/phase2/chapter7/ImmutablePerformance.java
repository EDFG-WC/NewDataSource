package com.laowang.datasource.javaconcurrency.phase2.chapter7;

public class ImmutablePerformance {

  public static void main(String[] args) throws InterruptedException {
    long startTime = System.currentTimeMillis();
    SyncObj syncObj = new SyncObj();
    syncObj.setName("Alex");
    ImmutableObj immutableObj = new ImmutableObj("Alex");
    Thread t1 = new Thread() {
      @Override
      public void run() {
        for (int index = 0; index < 10_000_000; index++) {
          System.out.println(Thread.currentThread().getName() + " " + syncObj.toString());
        }
      }
    };
    t1.start();
    Thread t2 = new Thread() {
      @Override
      public void run() {
        for (int index = 0; index < 10_000_000; index++) {
          System.out.println(Thread.currentThread().getName() + " " + syncObj.toString());
        }
      }
    };
    t2.start();
    t1.join();
    t2.join();

    long endTime = System.currentTimeMillis();
    System.out.println("Elapsed time " + (endTime - startTime));
  }
}

class ImmutableObj {

  private final String name;

  public ImmutableObj(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "ImmutableObj{" +
        "name='" + name + '\'' +
        '}';
  }
}

class SyncObj {

  private String name;

  public synchronized void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "SyncObj{" +
        "name='" + name + '\'' +
        '}';
  }
}