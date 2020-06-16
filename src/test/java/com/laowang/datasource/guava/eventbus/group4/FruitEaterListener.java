package com.laowang.datasource.guava.eventbus.group4;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FruitEaterListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(FruitEaterListener.class);

    @Subscribe
    public void fruitTask(Fruit event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("The event [{}] will be handle by {}.{}", event, this.getClass().getSimpleName(),
                    "fruitTask");
        }
    }

    @Subscribe
    public void appleTask(Apple event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("The event [{}] will be handle by {}.{}", event, this.getClass().getSimpleName(),
                    "appleTask");
        }
    }

}
