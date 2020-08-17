package com.laowang.classloader;

public class LoaderClass {
    public static void main(String[] args) {
        MyObject myObject1 = new MyObject();
        MyObject myObject2 = new MyObject();
        MyObject myObject3 = new MyObject();
        MyObject myObject4 = new MyObject();
        // 同一种类的对象, 它的class文件是一样的:
        System.out.println(myObject1.getClass() == myObject2.getClass());
        System.out.println(myObject2.getClass() == myObject3.getClass());
        System.out.println(myObject3.getClass() == myObject4.getClass());
    }
}

class MyObject {

}