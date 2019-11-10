package com.laowang.datasource.JavaConcurrency.chapter11;

public class ExitCapture {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("This app will exit.");
            notifyAndRelase();
        }));
        int i = 0;
        while (true) {
            try {
                System.out.println("This app is working.");
                Thread.sleep(1_000L);
            } catch (Throwable e) {
            }
            i++;
            if (i > 20) {
                throw new RuntimeException("error");
            }
        }
    }

    private static void notifyAndRelase() {
        System.out.println("Notify to admin.");
        try {
            Thread.sleep(1_000);
        } catch (Throwable e) {
        }
        System.out.println("resource(socket, file, connection) will be released.");
        try {
            Thread.sleep(1_000);
        } catch (Throwable e) {
        }
        System.out.println("Released and notified.");
    }
}
