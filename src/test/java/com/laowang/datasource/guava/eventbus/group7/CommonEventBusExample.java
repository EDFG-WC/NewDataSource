package com.laowang.datasource.guava.eventbus.group7;

import com.google.common.eventbus.EventBus;

public class CommonEventBusExample {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        QueryService queryService = new QueryService(eventBus);
        RequestQueryHandler requestQueryHandler = new RequestQueryHandler(eventBus);
        queryService.query("launchOrder9527");
    }
}
