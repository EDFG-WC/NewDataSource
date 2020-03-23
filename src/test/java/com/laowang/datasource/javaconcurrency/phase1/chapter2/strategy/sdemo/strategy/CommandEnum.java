package com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo.strategy;

import java.util.HashMap;
import java.util.Map;
// 通过枚举的方式, 声明出三种不同策略.
public enum CommandEnum {
  SELECT("select",
      "com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo.command.SelectCommand"),
  ADD("add",
      "com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo.command.AddCommand"),
  ABORT("abort",
      "com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo.command.AbortCommand");

  // 是不是看起来越来越像是一个普通的JavaBean?
  private final String command;
  private final String clazz;
  // 通过尝试, 枚举不允许public修饰构造函数
  CommandEnum(String command, String clazz) {
    this.command = command;
    this.clazz = clazz;
  }

  public String getCommand() {
    return command;
  }

  public String getClazz() {
    return clazz;
  }

  public static Map<String, String> getAllClazz() {
    Map<String, String> map = new HashMap<>();
    for (CommandEnum commandEnum : CommandEnum.values()) {
      map.put(commandEnum.getCommand(), commandEnum.getClazz());
    }
    return map;
  }
}
