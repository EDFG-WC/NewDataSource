package com.laowang.datasource.javaconcurrency.phase2.chapter13;

import java.util.LinkedList;

public class MessageQueue {

  private final LinkedList<Message> queque;
  private final static int DEFAULT_MAX_LIMIT = 100;
  private final int limit;

  public MessageQueue() {
    this(DEFAULT_MAX_LIMIT);
  }

  public MessageQueue(final int limit) {
    this.limit = limit;
    this.queque = new LinkedList<>();
  }

  public void put(Message message) throws InterruptedException {
    synchronized (queque) {
      while (queque.size() > limit) {
        queque.wait();
      }
      queque.addLast(message);
      queque.notifyAll();
    }
  }

  public Message take() throws InterruptedException {
    synchronized (queque) {
      while (queque.isEmpty()) {
        queque.wait();
      }
      Message message = queque.removeFirst();
      queque.notifyAll();
      return message;
    }
  }

  public int getMaxLimit() {
    return this.limit;
  }

  public int getMessageSize() {
    synchronized (queque) {
      return queque.size();
    }
  }
}
