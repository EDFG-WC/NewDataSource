package com.laowang.datasource.guava.eventbus.listeners;

import com.google.common.eventbus.Subscribe;
import com.laowang.datasource.guava.eventbus.events.Apple;
import com.laowang.datasource.guava.eventbus.events.Fruit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FruitEaterListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(FruitEaterListener.class);

  @Subscribe
  public void eatTask(Fruit event) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("The event [{}] will be handle by {}.{}", event, this.getClass().getSimpleName(),
          "eatTask");
    }
  }

  @Subscribe
  public void eatTask(Apple event) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("The event [{}] will be handle by {}.{}", event, this.getClass().getSimpleName(),
          "eatTask");
    }
  }

}
