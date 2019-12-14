package com.laowang.datasource.javaconcurrency.phase2.chapter11.complicatedSafeBox;

import com.laowang.datasource.javaconcurrency.phase2.chapter11.simpleSafeBox.Context;

public class QueryFromDbAction {

  public void execute() {
    try {
      Thread.sleep(1_000L);
      String name = "Alex "+ Thread.currentThread().getName();
      ActionContext.getActionContext().getContext().setName(name);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
