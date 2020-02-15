package com.laowang.datasource.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.laowang.datasource.guava.eventbus.events.Apple;
import com.laowang.datasource.guava.eventbus.listeners.ConcreteListener;
import com.laowang.datasource.guava.eventbus.listeners.FruitEaterListener;

public class FruitEventBusExample {

  // 字类的listener调用, 父类也会被调用
  public static void main(String[] args) {
    final EventBus eventBus = new EventBus();
    eventBus.register(new FruitEaterListener());
    System.out.println("post the string event.");
    eventBus.post(new Apple("apple"));

  }
}
