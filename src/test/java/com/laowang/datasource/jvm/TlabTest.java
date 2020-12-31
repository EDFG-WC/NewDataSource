package com.laowang.datasource.jvm;
// -XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:-UseTLAB
// -XX:-这个减号代表去掉这个属性
public class TlabTest {
    class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    void alloc(int i) {
        new User(i, "name" + i);
    }

    public static void main(String[] args) {
        TlabTest test = new TlabTest();
        long start = System.currentTimeMillis();
        for (int index = 0; index < 1000_0000; index++) {
            test.alloc(index);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
