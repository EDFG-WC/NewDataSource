package com.laowang.classloader;

public class Singleton {
    private static Singleton instance = new Singleton(); //执行结果x是0, y是1
    /**
     * 加载: instance = null; x = 0; y = 0;
     * 初始化: new instance=> x++ => x = 1; y++ => y = 1;
     * 初始化: x = 0;
     * 初始化: y(本身没有赋值动作, 用已经赋过的值)
     */
    public static int x = 0;
    public static int y;
//    private static Singleton instance = new Singleton(); // 执行结果x, y都是1

    /**
     * 加载: x=0; y=0; instance=null;
     * 初始化: new instance=> x++ => x = 1; y++ => y = 1;
     */
    private Singleton() {
        x++;
        y++;
    }

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        final Singleton instance = getInstance();
        System.out.println(instance.x);
        System.out.println(instance.y);
    }
}
