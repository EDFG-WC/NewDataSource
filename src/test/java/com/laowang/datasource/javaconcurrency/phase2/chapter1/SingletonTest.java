package com.laowang.datasource.javaconcurrency.phase2.chapter1;

public class SingletonTest {
  ;

  private SingletonTest() {
  }

  public static SingletonTest getInstance() {
    return SingletonHolder.INSTANCE;
  }

  private static class SingletonHolder {

    private static final SingletonTest INSTANCE = new SingletonTest();
  }
}
