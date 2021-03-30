package com.laowang.datasource.javaconcurrency.msb.juc.c_000;

public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
        // testSleep();
        // testYield();
        testJoin();
        
    }

    /**
     * sleep会把当前线程暂停一段时间让给别的线程去运行. 到了时间点之后自动复活.
     */
    static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                    // TimeUnit.Milliseconds.sleep(500)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * yield把当前线程从正在执行状态停下返回到等待队列. 此时再由调度算法重新从等待队列里重新拿一个出来. 有可能会再把执行yield的方法执行一次, 但一般不会这样. yield就是我要出让一下CPU, 谁会抢到执行权不清楚.
     */
    static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                if (i % 10 == 0)
                    Thread.yield();

            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("------------B" + i);
                if (i % 10 == 0)
                    Thread.yield();
            }
        }).start();
    }

    /**
     * join让指定的线程启动, 而执行join方法的线程进入等待, 指定线程结束后才会继续执行. e.g: t1线程执行了t2.join()之后, t2执行, t1等待t2执行完毕后继续执行 自己join自己没有意义.
     */
    static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(10);
                    // TimeUnit.Milliseconds.sleep(500)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {

//            try {
//                t1.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            for (int i = 0; i < 100; i++) {
                System.out.println("B" + i);
                try {
                    Thread.sleep(10);
                    // TimeUnit.Milliseconds.sleep(500)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
