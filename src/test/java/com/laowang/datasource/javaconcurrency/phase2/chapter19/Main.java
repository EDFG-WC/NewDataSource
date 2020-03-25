package com.laowang.datasource.javaconcurrency.phase2.chapter19;

/**
 * 对于这种设计模式, 主线程不会阻塞, 但确实异步调用了某些方法. 跟System.gc()类似.
 */
public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();

        new MakerClientThread("Alice", activeObject).start();
        new MakerClientThread("Bobby", activeObject).start();

        new DisplayClientThread("Chris", activeObject).start();
    }
}
