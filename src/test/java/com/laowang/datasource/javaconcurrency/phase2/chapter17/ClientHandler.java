package com.laowang.datasource.javaconcurrency.phase2.chapter17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

  private final Socket socket;

  private volatile boolean running = true;

  public ClientHandler(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    // try-with-resource这个机制非常棒, 写在try后面()里的东西就不用我们自己去关了!!
    try (InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream(); // 对于我们来说是out对于计算机来说是in.
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
      e.printStackTrace();
      this.running = true;
    }
  }

  public void stop() {
    if (running) {
      return;
    }
    this.running = false;
    try {
      this.socket.close();
    } catch (IOException e) {
      System.out.println("current socket failed to close."); //关不了就关不了, 不要了.
    }
  }
}
