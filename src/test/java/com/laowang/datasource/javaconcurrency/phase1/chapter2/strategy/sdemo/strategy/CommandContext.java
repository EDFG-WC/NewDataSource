package com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy.sdemo.strategy;

import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class CommandContext {

  public static CommandStrategy getInstance(String commandType) {
    CommandStrategy commandStrategy = null;
    Map<String, String> allClazz = CommandEnum.getAllClazz();
    String clazz = allClazz.get(commandType.trim().toLowerCase(Locale.ENGLISH));
    if (StringUtils.isNotBlank(clazz)) {
      try {
        commandStrategy = (CommandStrategy) Class.forName(clazz).newInstance();
        commandStrategy.process(commandType);
      } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
    return commandStrategy;
  }


}
