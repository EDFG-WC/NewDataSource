package com.laowang.datasource.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.laowang.datasource.guava.eventbus.listeners.MultipleEventListeners;

public class MultipleEventBusExample {

  public static void main(String[] args) {
    final EventBus eventBus = new EventBus();
    eventBus.register(new MultipleEventListeners());
    System.out.println("post the string event.");
    eventBus.post("I am a string event.");
    System.out.println("post the int event.");
    eventBus.post(1000);
  }
}
