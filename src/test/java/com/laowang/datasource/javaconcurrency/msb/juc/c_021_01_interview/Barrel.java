package com.laowang.datasource.javaconcurrency.msb.juc.c_021_01_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Barrel {
    public static List<String> barrel = new ArrayList<String>();
    final static Object LOCK = new Object();
    final static int MAX_SIZE = 10;

    static class Farmer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    if (barrel.size() == MAX_SIZE) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    barrel.add("apple");
                    System.out.println("农夫放了一个水果,目前 + " + barrel.size() + "个水果");
                    LOCK.notify();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Child extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    if (barrel.size() == 0) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    barrel.remove("apple");
                    System.out.println("小孩吃了一个水果,目前筐里有" + barrel.size() + "个水果");
                    LOCK.notify();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

  public static void main(String[] args) {
    new Farmer().start();
    new Child().start();
  }
}
