package com.laowang.datasource.guava.eventbus.group1;

import com.google.common.eventbus.EventBus;

/**
 * eventBus的生产/发送方:
 */
public class SimpleEventBusExample {

  public static void main(String[] args) {
    final EventBus eventBus = new EventBus();
    // 指定接收方:
    eventBus.register(new SimpleListener());
    System.out.println("post the simple event.");
    // 向SimpleListener发送消息(只能有一个参数)
    eventBus.post("Simple Event");
  }
}
