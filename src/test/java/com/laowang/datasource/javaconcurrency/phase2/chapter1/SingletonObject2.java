package com.laowang.datasource.javaconcurrency.phase2.chapter1;

/**
 * 懒汉式单例
 */
public class SingletonObject2 {

  /**
   * 声明但不创建对象, 不能加final
   */
  private static SingletonObject2 instance;

  /**
   * 私有化构造函数
   */
  private SingletonObject2() {
  }

  public static SingletonObject2 getInstance() {
    // 多个线程对这个方法的调用会导致单例失效:
    if (instance == null) {
      instance = new SingletonObject2();
    }
    // SingletonObject2.instance一眼就能看出来instance是哪个类的对象
    return SingletonObject2.instance;
  }
}
