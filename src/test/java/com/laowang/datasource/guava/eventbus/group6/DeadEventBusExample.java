package com.laowang.datasource.guava.eventbus.group6;

import com.google.common.eventbus.EventBus;
import jdk.nashorn.internal.ir.CallNode;

public class    DeadEventBusExample {
    public static void main(String[] args) {
    final DeadEventListener listener = new DeadEventListener();
        final EventBus eventBus = new EventBus("DeadEventBus"){
            @Override
            public String toString() {
                return "DEAD_EVENT_BUS";
            }
        };
        eventBus.register(listener);
        eventBus.post("Hello");
        eventBus.post("Hello");
        // 注销监听:
        eventBus.unregister(listener);
        // 注销之后再post就不管用了
        eventBus.post("Hello");
    }
}
