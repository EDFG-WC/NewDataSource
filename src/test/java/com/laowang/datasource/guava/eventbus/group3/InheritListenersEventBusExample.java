package com.laowang.datasource.guava.eventbus.group3;

import com.google.common.eventbus.EventBus;
import com.laowang.datasource.guava.eventbus.group3.ConcreteListener;

public class InheritListenersEventBusExample {

  // 子类的listener调用, 父类也会被调用(这就是特性, 看你怎么利用了)
  public static void main(String[] args) {
    final EventBus eventBus = new EventBus();
    eventBus.register(new ConcreteListener());
    System.out.println("post the string event.");
    eventBus.post("I am a string event.");
    System.out.println("post the int event.");
    // 没有listener是接收Integer类event的所以这个没起作用
    eventBus.post(1000);
  }
}
