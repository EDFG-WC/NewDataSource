package com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo;

import com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo.strategy.CommandContext;
import com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo.strategy.CommandStrategy;

public class TestMain {
  public static void main(String[] args) {
    String commandType = "select";
    CommandStrategy instance = CommandContext.getInstance(commandType);
    instance.process(commandType);
  }
}