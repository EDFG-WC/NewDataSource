/**
 * ��N�Ż�Ʊ��ÿ��Ʊ����һ����� ͬʱ��10�����ڶ�����Ʊ ��дһ��ģ�����
 * 
 * ��������ĳ�����ܻ������Щ���⣿ �ظ����ۣ��������ۣ�
 * 
 * ʹ��Vector����Collections.synchronizedXXX ����һ�£������ܽ��������
 * 
 * �������A��B����ͬ���ģ���A��B��ɵĸ��ϲ���Ҳδ����ͬ���ģ���Ȼ��Ҫ�Լ�����ͬ�� ������������ж�size�ͽ���remove������һ������ԭ�Ӳ���
 * 
 * ʹ��ConcurrentQueue��߲�����
 * 
 * @author ��ʿ��
 */
package com.laowang.datasource.javaconcurrency.msb.juc.c_024_FromVectorToQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketSeller4 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号: " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String s = tickets.poll();
                    if (s == null)
                        break;
                    else
                        System.out.println("销售了--" + s);
                }
            }).start();
        }
        ConcurrentHashMap map = new ConcurrentHashMap();

    }
}
