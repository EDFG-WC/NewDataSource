package com.laowang.datasource.javaconcurrency.phase2.chapter19;

public class MakerClientThread extends Thread {
    private final ActiveObject activeObject;
    private final char fillChar;

    public MakerClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillChar = name.charAt(0);
    }

    @Override public void run() {
        try {
            for (int index = 0; true; index++) {
                Result result = activeObject.makeString(index + 1, fillChar);
                Thread.sleep(20);
                String value = (String)result.getResultValue();
                System.out.println(Thread.currentThread().getName() + " :value=" + value);
            }
        } catch (Exception e) {
        }
    }
}
