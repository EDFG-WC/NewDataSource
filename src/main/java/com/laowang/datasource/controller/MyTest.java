package com.laowang.datasource.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyTest {
    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();
        UserService userService = new UserService();
        Class<? extends UserController> clazz = userController.getClass();
        Field serviceField = clazz.getDeclaredField("userService");
        serviceField.setAccessible(true);
        String name = serviceField.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
        String methodName = "set" + name;
        Method method = clazz.getMethod(methodName, UserService.class);
        method.invoke(userController, userService);
    }
}
