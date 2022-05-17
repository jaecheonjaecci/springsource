package com.company.factorial;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect //일반객체가 아닌 aop 담당해줄 클래스이기 때문에 선언
public class TimeAspect {

	// around 사용시 ProceedingJoinPoint 객체를 호출해야함 -> 메소드들을 호출해줌
	@Around(value="execution(* com.company.factorial..*(..))")
	public Object measure(ProceedingJoinPoint pjp) throws Throwable {

		// 시작시간
		long start = System.nanoTime();

		// 실제 수행 메소드 호출하기
		try {
			Object obj = pjp.proceed();
			return obj;
		} finally {
			// 종료 시간
			long end = System.nanoTime();
			System.out.println("걸린시간 : "+(end-start));

		}

	}
}
