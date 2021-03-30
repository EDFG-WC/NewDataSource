/**
 * һ��ͬ���������Ե�������һ��ͬ��������һ���߳��Ѿ�ӵ��ĳ������������ٴ������ʱ����Ȼ��õ��ö������.
 * Ҳ����˵synchronized��õ����ǿ������
 * @author mashibing
 */
package com.laowang.datasource.javaconcurrency.msb.juc.c_009;

import java.util.concurrent.TimeUnit;

/**
 * 在这个例子里, 2个方法都有类锁, 那么m1方法调用m2, m2能拿到这把类锁吗? 当然可以的. 这就是一个可重入锁的例子. synchronized就是一个可重入锁
 */
public class T {
	synchronized void m1() {
		System.out.println("m1 start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m2();
		System.out.println("m1 end");
	}
	
	synchronized void m2() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m2");
	}

	public static void main(String[] args) {
		new T().m1();
	}
}
