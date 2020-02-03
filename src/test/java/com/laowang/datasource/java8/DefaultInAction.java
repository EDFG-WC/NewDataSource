package com.laowang.datasource.java8;

public class DefaultInAction {

  public static void main(String[] args) {
    // 首先, 在使用接口的时候, 用lambda表达式的方式就可以不用再写实现类了, 这是非常棒的一个东西.
    // 接着, ()->10;和(num)->num*100;其实并不能简单地归纳为function, predicate, consumer, supplier或者runnable, ->左边代表的是参数, ->右边代表的是方法具体的实现;
    // 最后, default修饰的方法, 在实现某个接口的时候, 可以在有需要的时候重写, 没需要的时候忽略.
    B b = () -> 10;
    System.out.println(b.size());
    System.out.println(b.isEmpty());

    A a = (num) -> num * 100;
    System.out.println(a.size(10));
    System.out.println(a.isEmpty(10));

    DefaultInAction defaultInAction = new DefaultInAction();
    // 如果直接传null, 这里会调用的方法是confuse(int[] arr), 对于同名的2个方法来说, 因为confuse(int[] arr)的参数类型更具体, 所以它作为路径更短的方法被调用起来了.
    defaultInAction.confuse(null);
    // 这里的confuse调用的是confuse(Object obj)
    int[] arr = null;
    Object obj = arr;
    defaultInAction.confuse(obj);

    // C和D之间, E到底继承了谁?
    E e = new E();
    // 如果类E, 没有重写hello()方法, e.hello();得到的结果是D...hello===; 如果重写了hello()方法, 那么运行的一定是E的方法
    // 原因: 1. classes always win. default修饰的方法在这种继承关系里, 类/父类的方法优先级一定最高.
    //       2. sub-interfaces win. 一路传导, 继承下来的接口, 离得最近的接口中的同名方法优先级最高. 比如E同时实现了C和D(C继承了D), 但如果E本身没有重写hello(), 调用的一定是D的hello().
    //       3. 假设E继承了C和D, 但C和D之间没有继承关系, 这时C必须实现C和D都有的方法hello()方法, 编译器才能不报错.
    // 复习: 接口和接口之间只能继承不能实现, Java 8 之后, 接口可以写静态和default方法, 可以有方法体, 之前的接口里所有方法都不能有方法体
    e.hello();
  }

  // 可能引起混淆的方法:
  private void confuse(Object obj) {
    System.out.println("object");
  }

  private void confuse(int[] arr) {
    System.out.println("int[]");
  }

  private interface A {

    int size(int num);

    default boolean isEmpty(int num) {
      return size(num) == 0;
    }
  }

  private interface B {

    int size();

    default boolean isEmpty() {
      return size() == 0;
    }
  }

  private interface C {

    default void hello() {
      System.out.println("C...hello===");
    }
  }

  private interface D extends C {

    @Override
    default void hello() {
      System.out.println("D...hello===");
    }
  }

  private static class E implements C, D {

    @Override
    public void hello() {
      System.out.println("E...hello===");
    }
  }
}
