package com.laowang.datasource.javaconcurrency.phase2.chapter17.socketserver;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

// 接收客户端信息的类:
@Slf4j public class ClientHandler implements Runnable {

  private final Socket socket;

  private volatile boolean running = true;

  public ClientHandler(Socket socket) {
    this.socket = socket;
  }

  @Override public void run() {
    // try-with-resource这个机制非常棒, 写在try后面()里的东西就不用我们自己去关了!!
    try (InputStream inputStream = socket.getInputStream(); OutputStream outputStream = socket.getOutputStream();
        // 对于我们来说是out对于计算机来说是in.
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter printWriter = new PrintWriter(outputStream)) {
      while (running) {
        String message = bufferedReader.readLine();
        if (message == null) {
          break;
        }
        System.out.println("comes from client :" + message);
        printWriter.write("echo :" + message + "\n");
        printWriter.flush(); // 刷滑动窗口和管道
      }
    } catch (IOException e) {
      log.error("some thing wrong :", e);
    } finally { // 这里体现的就是2-phase termination pattern的地方: 出错了由finally代码块来做关闭.
      this.stop();
    }
  }

  public void stop() {
    log.info("current socket starts to stop.");
    if (!running) {
      return;
    }
    this.running = false;
    try {
      // 关闭socket对象
      this.socket.close();
    } catch (IOException e) {
      log.info("current socket failed to close."); //关不了就关不了, 不要了.
    }
    log.info("current socket stopped.");
  }
}
