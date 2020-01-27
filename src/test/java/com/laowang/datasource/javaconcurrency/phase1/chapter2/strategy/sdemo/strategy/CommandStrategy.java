package com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo.strategy;

public interface CommandStrategy {

  void process(String message);
}
