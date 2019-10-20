package com.laowang.datasource.JavaConcurrency.chapter1;

public class Test1 {
    private static void readFromDataBase() {
        try {
            print("Begin read data from db.");
            Thread.sleep(1000 * 10);
            print("Begin read data done and handle it.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        print("Read successfully");
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static void writeDataToFile() {
        try {
            print("Begin write data to file.");
            Thread.sleep(2000 * 10);
            print("Write data done and handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("Wrote successfully");
    }

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                readFromDataBase();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                writeDataToFile();
            }
        }.start();
    }
}
