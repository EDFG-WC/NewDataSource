/**
 * reentrantlock�������synchronized
 * ����m1����this,ֻ��m1ִ����ϵ�ʱ��,m2����ִ��
 * �����Ǹ�ϰsynchronized��ԭʼ������
 * 
 * ʹ��reentrantlock�������ͬ���Ĺ���
 * ��Ҫע����ǣ�����Ҫ����Ҫ����Ҫ�ֶ��ͷ�������Ҫ������˵���飩
 * ʹ��syn�����Ļ���������쳣��jvm���Զ��ͷ���������lock�����ֶ��ͷ�������˾�����finally�н��������ͷ�
 * 
 * ʹ��reentrantlock���Խ��С�����������tryLock�������޷�������������ָ��ʱ�����޷��������߳̿��Ծ����Ƿ�����ȴ�
 * @author mashibing
 */
package com.laowang.datasource.javaconcurrency.msb.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T03_ReentrantLock3 {
	Lock lock = new ReentrantLock();

	void m1() {
		try {
			lock.lock();
			for (int i = 0; i < 3; i++) {
				TimeUnit.SECONDS.sleep(1);

				System.out.println(i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 使用tryLock进行尝试锁定. 不管锁定与否, 方法都将继续执行
	 * 可以根据tryLock的返回判定是否锁定, 然后执行一些逻辑操作
	 * 也可以指定tryLock的时间, 使用的时候一定记得用try-finally包裹
	 */
	void m2() {
		boolean locked = false;
		
		try {
			locked = lock.tryLock(2, TimeUnit.SECONDS);
			System.out.println("m2 ..." + locked);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(locked) lock.unlock();
		}
	}

	public static void main(String[] args) {
		T03_ReentrantLock3 rl = new T03_ReentrantLock3();
		new Thread(rl::m1).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(rl::m2).start();
	}
}
