/**
 * �����������⣺���Ա����� ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 * 
 * ��lists���volatile֮��t2�ܹ��ӵ�֪ͨ�����ǣ�t2�̵߳���ѭ�����˷�cpu�����������ѭ��������ô���أ�
 * 
 * ����ʹ��wait��notify������wait���ͷ�������notify�����ͷ��� ��Ҫע����ǣ��������ַ���������Ҫ��֤t2��ִ�У�Ҳ����������t2�����ſ���
 * 
 * �Ķ�����ĳ��򣬲����������� ���Զ���������������size=5ʱt2�˳�������t1����ʱt2�Ž��յ�֪ͨ���˳� ��������Ϊʲô��
 * 
 * notify֮��t1�����ͷ�����t2�˳���Ҳ����notify��֪ͨt1����ִ�� ����ͨ�Ź��̱ȽϷ���
 * 
 * ʹ��Latch�����ţ����wait notify������֪ͨ �ô���ͨ�ŷ�ʽ�򵥣�ͬʱҲ����ָ���ȴ�ʱ�� ʹ��await��countdown�������wait��notify
 * CountDownLatch���漰��������count��ֵΪ��ʱ��ǰ�̼߳������� �����漰ͬ����ֻ���漰�߳�ͨ�ŵ�ʱ����synchronized + wait/notify���Ե�̫����
 * ��ʱӦ�ÿ���countdownlatch/cyclicbarrier/semaphore
 * 
 * @author mashibing
 */
package com.laowang.datasource.javaconcurrency.msb.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class T05_CountDownLatch {

    /*volatile*/ List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T05_CountDownLatch c = new T05_CountDownLatch();
        CountDownLatch latchOne = new CountDownLatch(1);
        CountDownLatch latchTwo = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2启动");
            try {
                // 不需要对size进行判断, 直接让当前线程等待就完了.
                // 当t1那边检测到size为5的时候, t1放开锁t2的门闩并等待, t2运行自己的逻辑，然后放开t1的门闩.
                latchOne.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 此时t2已经执行完了自己的业务逻辑, 放行t1.
            System.out.println(latchTwo.getCount());
            latchTwo.countDown();
            System.out.println("t2结束");
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);
                if (c.size() == 8) {
                    // 打开门闩, 让t2得以执行
                    latchOne.countDown();
                    try {
                        // 让当前线程等待
                        latchTwo.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }
}
