package com.laowang.datasource.javaconcurrency.phase2.chapter17.socketserver;

import java.io.IOException;

public class AppServerClient {

  public static void main(String[] args) throws InterruptedException, IOException {
    AppServer server = new AppServer(13345);
    server.start();
    Thread.sleep(30_000L);
    server.shutdown();
  }
}
