package com.laowang.datasource.javaconcurrency.phase2.chapter4.application;



public class ObservableRunnable implements Runnable {

  /**
   * listener就是一个observer
   */
  final protected LifecycleListener listener;

  public ObservableRunnable(final LifecycleListener listener) {
    this.listener = listener;
  }

  protected void notifyChange(final RunnableEvent event) {
    listener.onEvent(event);
  }

  public enum RunnableState {
    RUNNING, ERROR, DONE;
  }

  public static class RunnableEvent {

    private final RunnableState state;
    private final Thread thread;
    private final Throwable cause;

    public RunnableEvent(
        RunnableState state, Thread thread, Throwable cause) {
      this.state = state;
      this.thread = thread;
      this.cause = cause;
    }

    public RunnableState getState() {
      return state;
    }

    public Thread getThread() {
      return thread;
    }

    public Throwable getCause() {
      return cause;
    }
  }


  @Override
  public void run() {

  }
}
