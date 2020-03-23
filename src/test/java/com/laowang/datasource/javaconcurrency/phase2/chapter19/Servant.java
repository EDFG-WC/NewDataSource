package com.laowang.datasource.javaconcurrency.phase2.chapter19;

/**
 * 真正干活的类, 实现ActiveObject的类
 */
class Servant implements ActiveObject {
    @Override public Result makeString(int count, char fillChar) {
        char[] buf = new char[count];
        try {
            for (int index = 0; index < count; index++) {
                // buf里放了count个fillChar;
                buf[index] = fillChar;
                // 模拟耗时的操作:
                Thread.sleep(10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TrueResult(new String(buf));
    }

    @Override public void displayString(String text) {
        try {
            System.out.println("Display :" + text);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
