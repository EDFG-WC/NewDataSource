package com.laowang.datasource.javaconcurrency.phase2.chapter16;

import java.io.IOException;
import java.net.ServerSocket;

public class AppServer {

  // 假设我们有一个app的服务, 在start的时候(其实是监听), 我们会拿到一个链接, 接着我们拿着链接去工作, 然后停掉链接
  private final int port;
  private static final int DEFAULT_PORT = 10099;
  private boolean start = true;

  public AppServer() {
    this(DEFAULT_PORT);
  }

  public AppServer(int port) {
    this.port = port;
  }

  public void start() throws IOException {
    ServerSocket sever = new ServerSocket(port);
//    sever.accept()
  }
}
