package com.laowang.datasource.guava.eventbus.group2;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 若指定这个类为监听对象, task1和task2方法因为接收对象的类型一致, 可以接收到相同的内容, 而intTask则会接收到int类型的event
 */
public class MultipleEventListeners {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultipleEventListeners.class);

    @Subscribe
    public void task1(final String event) {

        if (LOGGER.isInfoEnabled()) {
            LOGGER
                    .info("Event [{}] has been received and will take a action by ===stringTask1===", event);
        }
    }

    @Subscribe
    public void task2(final String event) {

        if (LOGGER.isInfoEnabled()) {
            LOGGER
                    .info("Event [{}] has been received and will take a action by ===stringTask2===", event);
        }
    }

    @Subscribe
    // event必须是Integer
    public void intTask(final Integer event) {

        if (LOGGER.isInfoEnabled()) {
            LOGGER
                    .info("Event [{}] has been received and will take a action by ===intTask===", event);
        }
    }
}
