package com.laowang.datasource.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

/**
 * @Auther: wangc
 * @Date: 2021/5/12 19:58
 * @Description:
 */
@Rule(name = "既不被3整除也不被8整除", description = "打印number自己")
public class OtherRule {

	@Condition
	public boolean isOther(@Fact("number") int number) {
		return number % 3 != 0 || number % 8 != 0;
	}

	@Action
	public void printSelf(@Fact("number") int number) {
		System.out.print(number);
	}

	@Priority
	public int getPriority() {
		return 3;
	}
}