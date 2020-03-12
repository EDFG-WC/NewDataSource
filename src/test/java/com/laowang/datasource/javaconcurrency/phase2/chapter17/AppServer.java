package com.laowang.datasource.javaconcurrency.phase2.chapter17;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppServer extends Thread {

  // 在启动之后就开始监听, 拿到一个信息(socket链接)之后, 分一个线程给(thread per message)AppServer, 然后它去工作.
  // 工作过程中线程可能要关掉, 这个时候就要用two-phase termination 关掉线程. 在finally这里收回所有的资源.
  private int port;
  private static final int DEFAULT_PORT = 12722;

  private volatile boolean start = true;

  //  搞一个线程池: 节省启动关闭线程的资源
  private final static ExecutorService executor = Executors.newFixedThreadPool(10);

  // 把被扔进线程池的runnable implementations都记下.
  private List<ClientHandler> clientHandlers = new ArrayList<>();

  public AppServer() {
    this(DEFAULT_PORT);
  }

  public AppServer(int port) {
    this.port = port;
  }

  public void startServer() throws IOException {
    ServerSocket server = new ServerSocket(port); // 创建socket的异常要抛出, 否则出了问题, 调用者不知道发生了什么
  }

  public void shutdown() {
    this.start = false;
    this.interrupt();
  }

  @Override
  public void run() {
    try {
      ServerSocket server = new ServerSocket(port);
      while (start) {
        Socket client = server.accept();
        ClientHandler clientHandler = new ClientHandler(client);
        executor.submit(clientHandler);
        this.clientHandlers.add(clientHandler);
      }
    } catch (IOException e) {
      throw new RuntimeException();
    } finally {
      this.dispose();
    }
  }

  private void dispose() {
    this.clientHandlers.stream().forEach(ClientHandler::stop);
    this.executor.shutdown();
  }
}
