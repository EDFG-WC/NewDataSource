package com.laowang.datasource.guava.eventbus.group1;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * eventBus的监听/消费方
 */
public class SimpleListener {
    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleListener.class);

    @Subscribe // guava专用注解, 用来监听/订阅消息
    public void doAction(final String event) {

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Received event [{}] and will take a action", event);
        }
    }
}
