package com.laowang.datasource.controller;

import java.util.stream.Stream;

public class MyTest2 {
    public static void main(String[] args) {
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        Stream.of(clazz.getDeclaredFields()).forEach(field -> {
            // 获取属性是否有注解
            AutoInject annotation = field.getAnnotation(AutoInject.class);
            if (annotation != null) {
                field.setAccessible(true);
                // 获取当前属性的类型, 有了类型之后可以创建具体的对象
                Class<?> type = field.getType();
                // 创建对象
                try {
                    Object o = type.newInstance();
                    field.set(userController, o);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(userController.getUserService());
    }
}
