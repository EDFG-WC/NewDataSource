package com.laowang.datasource.javaconcurrency.phase2.chapter11.complicatedSafeBox;

import com.laowang.datasource.javaconcurrency.phase2.chapter11.simpleSafeBox.Context;

public class ActionContext {

  private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>() {
    @Override
    protected Context initialValue() {
      return new Context();
    }
  };

  /**
   * 如果一个类要被声明为static的，只有一种情况，就是静态内部类。如果在外部类声明为static，程序会编译都不会过。在一番调查后个人总结出了3点关于内部类和静态内部类（俗称：内嵌类）
   * 静态内部类使用场景一般是当外部类需要使用内部类，而内部类无需外部类资源，并且内部类可以单独创建的时候会考虑采用静态内部类的设计
   */
  public static class ContextHolder {

    private final static ActionContext actionContext = new ActionContext();
  }

  public static ActionContext getActionContext() {
    return ContextHolder.actionContext;
  }

  /**
   * 使用的时候注意在不同线程使用的时候, 注意清空.
   * @return
   */
  public Context getContext() {
    return threadLocal.get();
  }
}
