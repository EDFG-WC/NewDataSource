package com.laowang.datasource.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

/**
 * @Auther: wangc
 * @Date: 2021/5/12 19:27
 * @Description:
 */
@Rule(name = "被3整除", description = "number如果被3整除，打印：number is three")
public class ThreeRule {

	/**
	 * Condition:条件判断注解：如果return true， 执行Action
	 */
	@Condition
	public boolean isThree(@Fact("number") int number) {
		return number % 3 == 0;
	}

	/**
	 * Action 执行方法注解
	 */
	@Action
	public void threeAction(@Fact("number") int number) {
		System.out.println(number + " is three");
	}

	/**
	 * Priority:优先级注解：return 数值越小，优先级越高
	 */
	@Priority
	public int getPriority() {
		return 1;
	}
}
