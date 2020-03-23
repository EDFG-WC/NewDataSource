package com.laowang.datasource.javaconcurrency.phase2.chapter19;

public class ActiveObjectClient {
    public static void main(String[] args) {
        // 对于gc命令, 实际上是调用了另外的线程执行了gc.
        //        System.gc();
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread("Alice", activeObject).start();
        new MakerClientThread("Bobby", activeObject).start();

        new DisplayClientThread("Chris",activeObject).start();
    }
}
