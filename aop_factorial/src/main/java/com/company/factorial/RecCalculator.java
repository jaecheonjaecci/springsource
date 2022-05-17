package com.company.factorial;

import org.springframework.stereotype.Component;

@Component("rec")
public class RecCalculator implements Calculator {

	@Override
	public long factorial(long num) {
		//재귀호출 : 자기자신을 다시 호출하는 방식
		if(num==0) {
			return 1;
		}else {
			return num * factorial(num-1);

		}
		
		
	}

}
