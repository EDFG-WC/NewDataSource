package com.laowang.datasource.guava.eventbus.group4;

import com.google.common.eventbus.EventBus;
import com.laowang.datasource.guava.eventbus.group4.Apple;
import com.laowang.datasource.guava.eventbus.group4.FruitEaterListener;

public class FruitEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FruitEaterListener());
        System.out.println("post the string event.");
        // post的event是Apple的对象, 这意味着Fruit类的监听器也能监听到
        eventBus.post(new Apple("apple"));
        System.out.println("=====================================");
        eventBus.post(new Fruit("Banana"));
    }
}
