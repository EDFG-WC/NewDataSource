package com.laowang.datasource.javaconcurrency.phase2.chapter19;

/**
 * 执行代码的过程中, 存在阻塞, 所以返回这个类给客户. 搪塞客户.
 */
public class FutureResult implements Result {
    private Result result;

    private boolean ready = false;

    // 程序执行的真正结果用这个方法放进来.
    public synchronized void setResult(Result result) {
        this.result = result;
        this.ready = true;
        this.notifyAll();
    }

    @Override synchronized public Object getResultValue() {
        try {
            while (!ready) {
                this.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.result.getResultValue();
    }
}
