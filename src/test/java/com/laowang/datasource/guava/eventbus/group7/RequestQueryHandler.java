package com.laowang.datasource.guava.eventbus.group7;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestQueryHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(QueryService.class);
    private final EventBus eventBus;

    public RequestQueryHandler(EventBus eventBus) {
        this.eventBus = eventBus;
        // 把自己注册为listener:
        this.eventBus.register(this);
    }

    // 监听查询动作, 触发真正的查询:
    @Subscribe
    public void doQuery(Request request) {
        // 查询
        LOGGER.info("start to query the order [{}]", request.toString());
        // 查询结果
        Response response = new Response("you did it!");
        // 返回查询结果
        this.eventBus.post(response);
    }
}
