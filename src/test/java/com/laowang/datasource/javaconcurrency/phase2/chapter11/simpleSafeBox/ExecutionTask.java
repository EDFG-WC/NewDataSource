package com.laowang.datasource.javaconcurrency.phase2.chapter11.simpleSafeBox;

/**
 * 这个类里不用写真正的业务, 直接把业务做完传进来
 */
public class ExecutionTask implements Runnable {

  /**
   * 模拟数据库查询
   */
  private QueryFromDbAction queryAction = new QueryFromDbAction();

  /**
   * 模拟调用别人的接口查询
   */
  private QueryFromHttpAction httpAction = new QueryFromHttpAction();

  @Override
  public void run() {
    // 从db里拿到数据放进action, 然后放进context
    final Context context = new Context();
    queryAction.execute(context);
    System.out.println("The name query successfully");
    httpAction.execute(context);
    System.out.println("The card id query successfully");
    System.out
        .println("The name is " + context.getName() + " and card id is " + context.getCardId());
  }
}
