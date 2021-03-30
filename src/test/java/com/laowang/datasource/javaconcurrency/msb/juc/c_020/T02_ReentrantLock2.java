/**
 * reentrantlock�������synchronized
 * ����m1����this,ֻ��m1ִ����ϵ�ʱ��,m2����ִ��
 * �����Ǹ�ϰsynchronized��ԭʼ������
 * 
 * ʹ��reentrantlock�������ͬ���Ĺ���
 * ��Ҫע����ǣ�����Ҫ����Ҫ����Ҫ�ֶ��ͷ�������Ҫ������˵���飩
 * ʹ��syn�����Ļ���������쳣��jvm���Զ��ͷ���������lock�����ֶ��ͷ�������˾�����finally�н��������ͷ�
 * @author mashibing
 */
package com.laowang.datasource.javaconcurrency.msb.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T02_ReentrantLock2 {
	Lock lock = new ReentrantLock();

	void m1() {
		try {
			// lock和unlock方法一定要写在try-finally里, 否则一旦代码报异常, 锁将永远无法被解锁.
			lock.lock(); //synchronized(this)
			for (int i = 0; i < 10; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	void m2() {
		try {
			lock.lock();
			System.out.println("m2 ...");
		} finally {
			lock.unlock();
		}

	}

	public static void main(String[] args) {
		T02_ReentrantLock2 rl = new T02_ReentrantLock2();
		new Thread(rl::m1).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(rl::m2).start();
	}
}
