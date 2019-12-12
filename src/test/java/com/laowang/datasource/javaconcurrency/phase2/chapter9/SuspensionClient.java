package com.laowang.datasource.javaconcurrency.phase2.chapter9;

public class SuspensionClient {

  public static void main(String[] args) throws InterruptedException {
    // server和client是RequestQueue来桥接的
    final RequestQueue queue = new RequestQueue();
    new ClientThread(queue, "Alex").start();
    ServerThread serverThread = new ServerThread(queue);
    serverThread.start();
    // join是等待线程完成, 如果那边wait了是完成不了的, 所以不行
    // serverThread.join();
    Thread.sleep(1000);
    serverThread.close();
  }
}
