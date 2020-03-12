package com.laowang.datasource.javaconcurrency.phase2.chapter17;

public class AppServerClient {

  public static void main(String[] args) {
    AppServer server = new AppServer(13345);
    server.start();
  }
}
