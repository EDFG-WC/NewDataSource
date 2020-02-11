package com.laowang.datasource.guava.utilities;

import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StopWatchExample {

  private static final Logger LOGGER = LoggerFactory.getLogger(StopWatchExample.class);

  public static void main(String[] args) throws InterruptedException {
    process("3463542353");
  }

  /**
   * splunk
   *
   * @param orderNo
   * @throws InterruptedException
   */
  private static void process(String orderNo) throws InterruptedException {

    System.out.printf("start process the order %s\n", orderNo);
    Stopwatch stopwatch = Stopwatch.createStarted();
    TimeUnit.SECONDS.sleep(1);

    LOGGER.info("The orderNo [{}] process successful and elapsed [{}].", orderNo,
        stopwatch.stop());
  }
}
