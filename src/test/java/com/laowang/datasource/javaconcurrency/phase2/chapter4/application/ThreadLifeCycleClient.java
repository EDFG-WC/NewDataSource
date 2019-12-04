package com.laowang.datasource.javaconcurrency.phase2.chapter4.application;

import java.util.Arrays;

public class ThreadLifeCycleClient {

  public static void main(String[] args) {
    new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1","2","3"));
  }
}
