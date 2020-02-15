package com.laowang.datasource.guava.eventbus.listeners;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultipleEventListeners {

  private static final Logger LOGGER = LoggerFactory.getLogger(MultipleEventListeners.class);

  @Subscribe
  public void task1(final String event) {

    if (LOGGER.isInfoEnabled()) {
      LOGGER
          .info("Event [{}] has been received and will take a action by ===task1===", event);
    }
  }

  @Subscribe
  public void task2(final String event) {

    if (LOGGER.isInfoEnabled()) {
      LOGGER
          .info("Event [{}] has been received and will take a action by ===task2===", event);
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
