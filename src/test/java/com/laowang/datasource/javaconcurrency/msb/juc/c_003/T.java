/**
 * synchronized�ؼ���
 * ��ĳ���������
 * @author mashibing
 */

package com.laowang.datasource.javaconcurrency.msb.juc.c_003;

public class T {

	private int count = 10;
	
	public synchronized void m() { //��ͬ���ڷ����Ĵ���ִ��ʱҪsynchronized(this)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}


}
