package com.laowang.datasource.javaconcurrency.phase2.chapter1;

/**
 * 懒汉式单例
 */
public class SingletonObject3 {

  /**
   * 声明但不创建对象, 不能加final
   */
  private static SingletonObject3 instance;

  /**
   * 私有化构造函数
   */
  private SingletonObject3() {
  }

  /**
   * 如果在这里加这个synchronized, 在创建对象的时候可以保证单例
   * 但如果if判断的条件不成立, 仅仅是拿到instance对象,
   * synchronized又导致了一个串行化的问题: 所有的线程到这里都要排队
   * @return
   */
  public synchronized static SingletonObject3 getInstance() {
    if (instance == null) {
      instance = new SingletonObject3();
    }
    // SingletonObject3.instance一眼就能看出来instance是哪个类的对象
    return SingletonObject3.instance;
  }
}
