package com.laowang.datasource.javaconcurrency.phase2.chapter1;

/**
 * 懒汉式单例
 */
public class SingletonObject5 {

  /**
   * 声明但不创建对象, 不能加final, volatile不能保证原子性, 但可以保证内存的可见性(多个线程看到的都是同一份, 同时要求JVM不去优化)
   * volatile可以让这个对象遵循happens-before规则, 就是这个对象在读之前, 它的写操作一定要完成.
   */
  private static volatile SingletonObject5 instance;

  /**
   * 私有化构造函数
   */
  private SingletonObject5() {

  }

  // double check
  public static SingletonObject5 getInstance() {
    if (null == instance) {
      synchronized (SingletonObject5.class) {
        if (null == instance) {
          instance = new SingletonObject5();
        }
      }
    }
    return SingletonObject5.instance;
  }
}
