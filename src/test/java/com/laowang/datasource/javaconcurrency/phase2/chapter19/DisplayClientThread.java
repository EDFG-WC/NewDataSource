package com.laowang.datasource.javaconcurrency.phase2.chapter19;

/**
 * 发出“显示字符串”请求的线程
 */
public class DisplayClientThread extends Thread {
    private final ActiveObject activeObject;

    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override public void run() {
        try {
            for (int index = 0; true; index++) {
                String text = Thread.currentThread().getName() + " =>" + index;
                activeObject.displayString(text);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
