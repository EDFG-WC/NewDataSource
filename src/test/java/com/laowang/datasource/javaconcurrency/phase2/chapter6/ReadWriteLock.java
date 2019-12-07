package com.laowang.datasource.javaconcurrency.phase2.chapter6;

public class ReadWriteLock {

  /**
   * 读操作线程数量
   */
  private int readingReaders = 0;
  /**
   * 读操作等待线程数量
   */
  private int waitingReaders = 0;
  /**
   * 写操作线程数量
   */
  private int writingWriters = 0;
  /**
   * 写操作等待数量
   */
  private int waitingWriters = 0;

  /**
   * 写线程偏好开关: 读线程比写线程多, 写线程可能抢不到锁, 所以要在这里设置一个开关
   */
  private boolean preferWriterSwitch;

  /**
   * 默认为true
   */
  public ReadWriteLock() {
    this(true);
  }

  public ReadWriteLock(boolean preferWriterSwitch) {
    this.preferWriterSwitch = preferWriterSwitch;
  }

  /**
   * 读锁
   *
   * @throws InterruptedException
   */
  public synchronized void readLock() throws InterruptedException {
    this.waitingReaders++;
    try {
      // 写操作进行时, 绝不进行读操作; (写线程偏爱开关如果打开)有等待的写线程, 读线程也要等待.
      while (writingWriters > 0 || (preferWriterSwitch && waitingWriters > 0)) {
        this.wait();
      }
      this.readingReaders++;
    } finally {
      this.waitingReaders--;
    }
  }

  /**
   * 解开读锁
   */
  public synchronized void readUnlock() {
    this.readingReaders--;
    this.notifyAll();
  }

  /**
   * 写锁
   *
   * @throws InterruptedException
   */
  public synchronized void writeLock() throws InterruptedException {
    this.waitingReaders++;
    try {
      while (readingReaders > 0 || writingWriters > 0) {
        this.wait();
      }
      this.writingWriters++;
    } finally {
      this.waitingWriters--;
    }
  }

  /**
   * 解写锁
   */
  public synchronized void writeUnlock() {
    this.writingWriters--;
    this.notifyAll();
  }
}
