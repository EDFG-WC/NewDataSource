package com.laowang.datasource.javaconcurrency.phase2.chapter17.socketserver;

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

  private ServerSocket server;

  public AppServer() {
    this(DEFAULT_PORT);
  }

  public AppServer(int port) {
    this.port = port;
  }

  public void startServer() throws IOException {
    ServerSocket server = new ServerSocket(port); // 创建socket的异常要抛出, 否则出了问题, 调用者不知道发生了什么
  }

  // 给调用者的提供的关闭方法. 用来关闭AppSever
  public void shutdown() throws IOException {
    this.start = false;
    this.interrupt();
    this.server.close();
  }

  @Override public void run() {
    try {
      server = new ServerSocket(port);
      System.out.println("启动了socket服务 :" + port);
      while (start) {
        Socket client = server.accept();
        // 用一个socket对象创建了clientHandler对象.
        ClientHandler clientHandler = new ClientHandler(client);
        // 虽然扔进了线程池, 但实际上只有一个线程被调用起来了, 不要混淆:
        executor.submit(clientHandler);
        this.clientHandlers.add(clientHandler);
      }
    } catch (IOException e) {
            throw new RuntimeException(); // 抛出之后, 后面的代码就无法正常运行了. 但finally里面的代码可以运行, 保证资源得到关闭.
    } finally {
      this.dispose();
    }
  }

  // 把线程逐一
  private void dispose() {
    System.out.println("App Server Dispose.");
    // 关闭所有socket对象, 当然我们这里实际上只有一个
    this.clientHandlers.stream().forEach(ClientHandler::stop);
    // 关闭线程池
    this.executor.shutdown();
  }
}
