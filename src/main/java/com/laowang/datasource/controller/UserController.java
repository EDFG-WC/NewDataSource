package com.laowang.datasource.controller;

public class UserController {
    @AutoInject
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
