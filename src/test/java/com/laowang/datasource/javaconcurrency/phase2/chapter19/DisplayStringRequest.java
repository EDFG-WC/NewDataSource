package com.laowang.datasource.javaconcurrency.phase2.chapter19;

/**
 * displayString方法（显示字符串）对应的类，MethodRequest类的子类
 */
public class DisplayStringRequest extends MethodRequest {
    private final String text;

    public DisplayStringRequest(Servant servant, final String text) {
        super(servant, null);
        this.text = text;
    }

    @Override public void execute() {
        this.servant.displayString(text);
    }
}
