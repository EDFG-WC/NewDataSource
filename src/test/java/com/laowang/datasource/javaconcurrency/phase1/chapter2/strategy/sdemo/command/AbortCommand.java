package com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo.command;

import com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo.strategy.CommandStrategy;

public class AbortCommand implements CommandStrategy {

  @Override
  public void process(String message) {
    System.out.println("command abort: " + message);
  }
}
