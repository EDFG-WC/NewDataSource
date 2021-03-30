/**
 * ThreadLocal�ֲ߳̾�����
 *
 * ThreadLocal��ʹ�ÿռ任ʱ�䣬synchronized��ʹ��ʱ�任�ռ�
 * ������hibernate��session�ʹ�����ThreadLocal�У�����synchronized��ʹ��
 *
 * ��������ĳ������ThreadLocal
 * 
 * @author ��ʿ��
 */
package com.laowang.datasource.javaconcurrency.msb.juc.c_022_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

public class ThreadLocal2 {
	//volatile static Person p = new Person();
	static ThreadLocal<Person> tl = new ThreadLocal<>();

	public static void main(String[] args) {
        /**
         * 这个线程, 我们给tl设置一个值, 但是在第二个线程是拿不到这个值的.
         */
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tl.set(new Person());
		}).start();

		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(tl.get());
		}).start();
	}
	
	static class Person {
		String name = "zhangsan";
	}
}


