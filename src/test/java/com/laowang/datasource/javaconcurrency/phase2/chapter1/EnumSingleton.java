package com.laowang.datasource.javaconcurrency.phase2.chapter1;

import java.util.stream.IntStream;

public class EnumSingleton {

  private EnumSingleton() {
  }

  /**
   * 枚举类型线程安全, 而且只会被装载一次:
   */
  private enum Singleton {
    // 枚举在定义INSTANCE的时候, instance对象已经被创建了
    INSTANCE;
    private final EnumSingleton instance;

    Singleton() {
      instance = new EnumSingleton();
    }

    public EnumSingleton getInstance() {
      return instance;
    }
  }

  public static EnumSingleton getInstance() {
    return Singleton.INSTANCE.getInstance();
  }

  // 测试单例效果:
  public static void main(String[] args) {
    IntStream.rangeClosed(1, 100).forEach(i -> new Thread(String.valueOf(i)) {
      @Override
      public void run() {
        System.out.println(EnumSingleton.getInstance());
      }
    }.start());
  }
}
