package com.laowang.datasource.javaconcurrency.phase2.chapter6;

public class ReadWriteLockClient {

  public static void main(String[] args) {
    final SharedData sharedData = new SharedData(10);
    new ReadWorker(sharedData).start();
    new ReadWorker(sharedData).start();
    new ReadWorker(sharedData).start();
    new WriteWorker(sharedData, "we are not saying hello world.").start();
    new WriteWorker(sharedData, "WE ARE NOT SAYING HELLO WORLD.").start();
  }
}
