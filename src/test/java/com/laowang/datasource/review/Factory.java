package com.laowang.datasource.review;





public class Factory {
    public void work(Car car, Client client) {
        final String gender = client.genderDefine();
        if (gender.equals("female")) {
            System.out.println("女生来洗车");
        } else {
            System.out.println("男生来洗车");
        }
        car.wash();
    }
}
