package com.laowang.datasource.javaconcurrency.phase2.chapter1;

/**
 * 经常使用的单例模式: 线程安全, 懒加载, 高效率
 */
public class BestSingleton {
  /**
   * 类加载的过程中的几个阶段:
   * 1.装载(读你的class文件)
   * 2.连接(相当于给类中的变量赋null值)
   * 3.初始化(初始化类变量)
   */

  /**
   * 私有化构造函数
   */
  private BestSingleton() {

  }

  private static class InstanceHolder {

    /**
     * 线程安全/单例: 对于instance来说, static让对象/类只会在类加载的时候执行一次, 所以可以严格地保证线程执行的顺序; 而且不会被创建第二个
     * 懒加载: 如果不调用getInstance方法, 这个对象就不会被加载, 因为InstanceHolder不会被掉调用, 所以保证了懒加载
     * 高效: 没有锁了, 当然高效!
     */
    private final static BestSingleton instance = new BestSingleton();
  }


  public static BestSingleton getInstance() {
    return InstanceHolder.instance;
  }
}
