package com.laowang.datasource.javaconcurrency.phase2.chapter4.application;

import com.laowang.datasource.javaconcurrency.phase2.chapter4.application.ObservableRunnable.RunnableEvent;

public interface LifecycleListener {

  void onEvent(RunnableEvent event);
}
