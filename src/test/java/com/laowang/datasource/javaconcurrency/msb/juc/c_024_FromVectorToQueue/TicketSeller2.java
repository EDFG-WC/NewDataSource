/**
 * ��N�Ż�Ʊ��ÿ��Ʊ����һ�����
 * ͬʱ��10�����ڶ�����Ʊ
 * ��дһ��ģ�����
 * 
 * ��������ĳ�����ܻ������Щ���⣿
 *  
 * ʹ��Vector����Collections.synchronizedXXX
 * ����һ�£������ܽ��������
 * 
 * @author ��ʿ��
 */
package com.laowang.datasource.javaconcurrency.msb.juc.c_024_FromVectorToQueue;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller2 {
	static Vector<String> tickets = new Vector<>();
	
	
	static {
		for(int i=0; i<10000; i++) tickets.add("票编号: " + i);
	}
	
	public static void main(String[] args) {
		
		for(int i=0; i<10; i++) {
			new Thread(()->{
				while(tickets.size() > 0) {
					
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("销售了--" + tickets.remove(0));
				}
			}).start();
		}
	}
}
