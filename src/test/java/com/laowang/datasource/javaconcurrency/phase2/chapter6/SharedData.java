package com.laowang.datasource.javaconcurrency.phase2.chapter6;

public class SharedData {

  private final char[] buffer;
  private final ReadWriteLock lock = new ReadWriteLock();

  /**
   * buffer里会默认保存size个*
   *
   * @param size
   */
  public SharedData(int size) {
    this.buffer = new char[size];
    for (int index = 0; index < buffer.length; index++) {
      buffer[index] = '*';
    }
  }

  /**
   * 读数据
   *
   * @return
   * @throws InterruptedException
   */
  public char[] read() throws InterruptedException {
    try {
      lock.readLock();
      return doRead();
    } finally {
      lock.readUnlock();
    }
  }

  public void write(char ch) throws InterruptedException {
    try {
      lock.writeLock();
      doWrite(ch);
    } finally {
      lock.writeUnlock();
    }
  }

  private void doWrite(char ch) {
    for (int index = 0; index < buffer.length; index++) {
      buffer[index] = ch;
      slowly(10);
    }
  }

  private char[] doRead() {
    char[] newBuffer = new char[buffer.length];
    for (int index = 0; index < buffer.length; index++) {
      newBuffer[index] = buffer[index];
    }
    slowly(50);
    return newBuffer;
  }

  private void slowly(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
