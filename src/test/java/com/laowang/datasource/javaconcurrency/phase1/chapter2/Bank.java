package com.laowang.datasource.javaconcurrency.phase1.chapter2;

public class Bank {
    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("1号柜台");
        ticketWindow1.start();
        TicketWindow ticketWindow2 = new TicketWindow("2号柜台");
        ticketWindow2.start();
        TicketWindow ticketWindow3 = new TicketWindow("3号柜台");
        ticketWindow3.start();
    }
}
