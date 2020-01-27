package com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo.strategy;

import java.util.HashMap;
import java.util.Map;

public enum CommandEnum {
  SELECT("select",
      "com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo.command.SelectCommand"),
  ADD("add",
      "com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo.command.AddCommand"),
  ABORT("abort",
      "com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo.command.AbortCommand");

  private final String command;
  private final String clazz;

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
