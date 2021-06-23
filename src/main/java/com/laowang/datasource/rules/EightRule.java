package com.laowang.datasource.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

/**
 * @Auther: wangc
 * @Date: 2021/5/12 19:55
 * @Description:
 */
@Rule(name = "被8整除")
public class EightRule {

	/**
	 * 条件
	 */
	@Condition
	public boolean isEight(@Fact("number") int number) {
		return number % 8 == 0;
	}

	/**
	 * 满足条件的动作
	 */
	@Action
	public void eightAction(@Fact("number") int number) {
		System.out.println(number + " is eight");
	}

	/**
	 * 条件判断的优先级
	 */
	@Priority
	public int getPriority() {
		return 2;
	}
}
