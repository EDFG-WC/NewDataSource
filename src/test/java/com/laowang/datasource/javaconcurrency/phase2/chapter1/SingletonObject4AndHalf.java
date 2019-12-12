package com.laowang.datasource.javaconcurrency.phase2.chapter1;

/**
 * 懒汉式单例
 */
public class SingletonObject4AndHalf {

  /**
   * 声明但不创建对象, 不能加final
   */
  private static SingletonObject4AndHalf instance;

  private Object obj1;
  private Object obj2;


  /**
   * 私有化构造函数
   */
  private SingletonObject4AndHalf() {
    obj1 = new Object();
    obj2 = new Object();
  }

  /**
   * 这样写, 功能实现了, 但性能仍然不好, 还可能会引起空指针异常? 为什么会引起空指针异常呢? 因为: JVM堆内存中需要创建三个对象的空间 instance obj1 obj2
   * 编译期间的顺序重排(JVM优化引起)引起的空指针: 一个类中有多个引用, 在编译期, 这些引用的加载顺序不是按照声明顺序来的, 这会导致当前类里的某个对象在没有初始化之前就被调用,
   * 从而出现空指针.
   *
   * @return instance
   */
  public static SingletonObject4AndHalf getInstance() {
    // 如果instance不为空就可以直接返回, 不需要再进同步锁里
    if (null == instance) {
      // 如果instance是空, 这个时候加个类锁, 让需要去创建对象的线程争抢这个锁, 不需要创建对象就不进锁
      synchronized (SingletonObject4AndHalf.class) {
        if (null == instance) {
          instance = new SingletonObject4AndHalf();
        }
      }
    }
    return SingletonObject4AndHalf.instance;
  }
}
