package com.laowang.datasource.guava.eventbus.group7;

public class Response {
    private final String responseMsg;

    public Response(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    @Override
    public String toString() {
        return "Response{" +
                "responseMsg='" + responseMsg + '\'' +
                '}';
    }
}
