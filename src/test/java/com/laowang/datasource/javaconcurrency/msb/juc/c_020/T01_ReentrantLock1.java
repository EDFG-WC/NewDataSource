/**
 * reentrantlock�������synchronized ����������m1����this,ֻ��m1ִ����ϵ�ʱ��,m2����ִ�� �����Ǹ�ϰsynchronized��ԭʼ������
 * 
 * @author mashibing
 */
package com.laowang.datasource.javaconcurrency.msb.juc.c_020;

import java.util.concurrent.TimeUnit;

/**
 * 重入锁的概念: 对于同一把锁(对象锁或类锁), 在外层使用锁之后, 内层仍然可以使用这个锁(比如本类的例子, 一个上锁的方法调用了另外一个使用相同锁的方法), 并且不会发生死锁
 */
public class T01_ReentrantLock1 {
    synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            // 这里调用了第二个使用相同锁的方法
            if (i == 2) m2();
        }

    }

    synchronized void m2() {
        System.out.println("m2 ...");
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 rl = new T01_ReentrantLock1();
        new Thread(rl::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // new Thread(rl::m2).start();
    }
}
