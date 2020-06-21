package com.laowang.datasource.guava.eventbus.group7;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryService {
    private final static Logger LOGGER = LoggerFactory.getLogger(QueryService.class);
    private final EventBus eventBus;

    public QueryService(EventBus eventBus) {
        // 保证请求发送方和接收方用的是一个eventBus
        this.eventBus = eventBus;
        // 把自己注册为listener:
        this.eventBus.register(this);
    }

    // 通过向消息总线发请求的方式发送请求:
    public void query(String orderNo) {
        LOGGER.info("Received an order [{}]", orderNo);
        this.eventBus.post(new Request(orderNo));
    }

    // 用注解接收响应
    @Subscribe
    public void handleResponse(Response response) {
        LOGGER.info("{} got the response: [{}]", this.getClass().getName(), response.toString());
    }
}
