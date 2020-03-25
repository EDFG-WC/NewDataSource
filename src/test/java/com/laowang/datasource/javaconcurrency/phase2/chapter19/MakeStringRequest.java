package com.laowang.datasource.javaconcurrency.phase2.chapter19;

/**
 * {@link ActiveObject#makeString(int, char)}
 * makeString方法（生成字符串）对应的类，MethodRequest类的子类
 */
public class MakeStringRequest extends MethodRequest {
    private final int count;
    private final char fillChar;

    public MakeStringRequest(Servant servant, FutureResult futureResult, int count, char fillChar) {
        super(servant, futureResult);
        this.count = count;
        this.fillChar = fillChar;
    }

    /**
     * 把每一个方法转换成对象
     */
    @Override public void execute() {
        Result result = servant.makeString(count, fillChar);
        futureResult.setResult(result);
    }
}
