package com.laowang.classloader;

class CacheImmutale {
    private static int MAX_SIZE = 10;
    //使用数组来缓存实例
    private static CacheImmutale[] cache = new CacheImmutale[MAX_SIZE];
    private static int pos = 0;
    private final String name;
    private CacheImmutale(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public static CacheImmutale valueOf(String name){
        for(int i=0; i < MAX_SIZE; i++){
            if(cache[i] != null && cache[i].getName().equals(name)){
                return cache[i];
            }
        }
        if(pos == MAX_SIZE){
            cache[0] = new CacheImmutale(name);
            pos = 1;
        }else{
            cache[pos++] = new CacheImmutale(name);
        }
        return cache[pos-1];
    }

    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj != null && obj.getClass() == CacheImmutale.class){
            CacheImmutale ci = (CacheImmutale)obj;
            return name.equals(ci.getName());
        }
        return false;
    }
    public int hashCode(){
        return name.hashCode();
    }
}
public class CacheImmutaleTest{
    public static void main(String[] args) {
        CacheImmutale c1 = CacheImmutale.valueOf("1");
        CacheImmutale c2 = CacheImmutale.valueOf("2");
        CacheImmutale c3 = CacheImmutale.valueOf("3");
        CacheImmutale c4 = CacheImmutale.valueOf("4");
        CacheImmutale c5 = CacheImmutale.valueOf("5");
        CacheImmutale c6 = CacheImmutale.valueOf("6");
        CacheImmutale c7 = CacheImmutale.valueOf("7");
        CacheImmutale c8 = CacheImmutale.valueOf("8");
        CacheImmutale c9 = CacheImmutale.valueOf("9");
        CacheImmutale c10 = CacheImmutale.valueOf("10");
        CacheImmutale c11 = CacheImmutale.valueOf("11");
        System.out.println(c1 == c2);
    }
}