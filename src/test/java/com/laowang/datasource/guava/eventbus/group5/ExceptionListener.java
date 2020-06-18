package com.laowang.datasource.guava.eventbus.group5;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionListener.class);

    @Subscribe
    public void m1(final String event) {
        LOGGER.info("==========m1=========={}", event);
    }

    @Subscribe
    public void m2(final String event) {
        LOGGER.info("==========m2=========={}", event);
    }

    @Subscribe
    public void m3(final String event) {
        LOGGER.info("==========m3=========={}", event);
        // 此处出了异常, 不会影响event bus正常运行. 异常会被event bus捕捉, 然后打印堆栈信息.
        throw new RuntimeException("出错啦!");
    }
}
