package com.laowang.datasource.javaconcurrency.phase2.chapter7;

final public class Person {

  /**
   * final修饰的成员变量一旦赋值后, 就不能被重新赋值了 final修饰的成员变量, 必须在定义的时候就给出初始值.(直接赋值, 普通代码块赋值, 构造器赋值(有参构造方法也算,
   * 创建对象的时候赋值就行了), 静态代码块赋值)
   */
  private final String name;
  private final String address;

  /**
   * 构造方法
   * @param name
   * @param address
   */
  // 形参用final修饰, Java1.7之后已经没有意义了, 1.7之前这样可以把对象加入常量池, 但随着电脑性能普遍提升, 1.7之后String全部都存常量池了.
  public Person(final String name,final String address) {
    this.name = name;
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", address='" + address + '\'' +
        '}';
  }
}
