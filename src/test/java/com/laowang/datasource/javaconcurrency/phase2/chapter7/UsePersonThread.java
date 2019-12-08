package com.laowang.datasource.javaconcurrency.phase2.chapter7;

public class UsePersonThread extends Thread {

  private Person person;

  public UsePersonThread(Person person) {
    this.person = person;
  }

  @Override
  public void run() {
    while (true) {
      System.out.println(Thread.currentThread().getName() + " print " + person);
    }
  }
}
