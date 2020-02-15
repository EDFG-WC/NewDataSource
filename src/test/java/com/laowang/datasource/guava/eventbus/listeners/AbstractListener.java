package com.laowang.datasource.guava.eventbus.listeners;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractListener.class);

  @Subscribe
  public void commonTask(final String event) {
    if (LOGGER.isInfoEnabled())
    {
      LOGGER.info("The event [{}] will be handle by {}.{}", event, this.getClass().getSimpleName(), "commonTask");
    }
  }
}
