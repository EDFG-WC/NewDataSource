package com.laowang.datasource.javaconcurrency.phase2.chapter4.application;

import com.laowang.datasource.javaconcurrency.phase2.chapter4.application.ObservableRunnable.RunnableEvent;
import java.util.List;

public class ThreadLifeCycleObserver implements LifecycleListener {

  // 有可能有多个线程 这里先加个锁.
  private final Object LOCK = new Object();

  public void concurrentQuery(List<String> ids) {
    if (ids.isEmpty() || ids == null) {
      return;
    }

    ids.stream().forEach(id -> {
      new Thread(new ObservableRunnable(this) {
        @Override
        public void run() {
          try {
            notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
            System.out.println("Query for the id: " + id);
            System.out.println("\r\n");
            Thread.sleep(1_000L);
            notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
          } catch (InterruptedException e) {
            notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
            e.printStackTrace();
          }
        }
      }, id).start();
    });
  }

  @Override
  public void onEvent(RunnableEvent event) {
    synchronized (LOCK) {
      System.out.printf("The runnable [%s] data changed and the state is [%s].",
          event.getThread().getName(), event.getState());
      System.out.println("\r\n");
      if (event.getCause() !=null){
        System.out.println("\r\n");
        System.out.printf("The runnable [%s] process failed.", event.getThread().getName());
        System.out.println("\r\n");
        event.getCause().printStackTrace();
      }
    }
  }
}
