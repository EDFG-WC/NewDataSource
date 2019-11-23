package com.laowang.datasource.javaconcurrency.phase1.chapter3;

public class CreateThread6 {

    private static int counter = 1;

    public static void main(String[] args) {
        try {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                counter++;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
            }
        } catch (Error e) {
//            e.printStackTrace();
        }
        System.out.println(counter);
    }
}
