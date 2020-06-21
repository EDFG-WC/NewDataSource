package com.laowang.datasource.guava.eventbus.group6;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

public class DeadEventListener {

    @Subscribe
    public void handle(DeadEvent event){
        // 获得event的来源
        System.out.println(event.getSource());
        System.out.println(event.getEvent());
    }
}
