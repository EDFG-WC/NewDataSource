package com.laowang.datasource.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.laowang.datasource.guava.eventbus.listeners.ConcreteListener;

public class InheritListenersEventBusExample {

  // 字类的listener调用, 父类也会被调用
  public static void main(String[] args) {
    final EventBus eventBus = new EventBus();
    eventBus.register(new ConcreteListener());
    System.out.println("post the string event.");
    eventBus.post("I am a string event.");
    System.out.println("post the int event.");
    eventBus.post(1000);
  }
}
