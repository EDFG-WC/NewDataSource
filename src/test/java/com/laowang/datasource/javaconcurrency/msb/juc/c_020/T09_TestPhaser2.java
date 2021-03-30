package com.laowang.datasource.javaconcurrency.msb.juc.c_020;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 模拟结婚场景, 使用Phaser
 */
public class T09_TestPhaser2 {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        phaser.bulkRegister(7);
        // 一共7个人参加婚礼.
        for (int i = 0; i < 5; i++) {

            new Thread(new Person("p" + i)).start();
        }

        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();

    }

    /**
     * 所有人(线程)都要按照这个步骤来 必须一个一个阶段地完成.
     */
    static class MarriagePhaser extends Phaser {
        /**
         * 当每个栅栏被推到当时候会自动调用
         * 
         * @param phase
         * @param registeredParties
         * @return
         */
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人到齐了!" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完了!" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了!" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束!新郎新娘抱抱! " + registeredParties);
                    return true;
                default:
                    return true;
            }
        }
    }

    /**
     * 模拟婚礼参与者
     */
    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达线程!\n", name);
            // 让当前线程到达此处, 并且等待其他线程.
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完!\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开!\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        /**
         * 只有新郎新娘可以使用的方法.
         */
        private void hug() {
            if (name.equals("新郎") || name.equals("新娘")) {
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 洞房!\n", name);
                phaser.arriveAndAwaitAdvance();
            } else {
                // 到达此处不再等待其他线程, 直接注销了. 意思是说本来一个线程结束要走完所有的流程. 但实际上要拥抱的人只有新郎新娘, 所以直接把其他人注销了.
                // 最终只有2个人算是完成了整个栅栏.
                phaser.arriveAndDeregister();
                // phaser.register()
            }
        }

        /**
         * Person的run方法. 模拟客人行为.
         */
        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }
}
