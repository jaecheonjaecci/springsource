package com.company.app;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect // aop annotion 설정방식
@Component("log")
public class LogAdvice {

	@Before(value = "execution(* com.company.app.Product.getInfo())")
	public void beforeLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 전 호출");
	}

	@After(value = "execution(* com.company.app.Product.getInfo())")
	public void afterLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출 - 예외와 상관없이 호출");
	}

	@AfterThrowing(value = "execution(* com.company.app.Product.getInfo())")
	public void afterThrowLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출 - 예외 발생 시 호출");
	}

	@AfterReturning(value = "execution(* com.company.app.Product.getInfo())")
	public void afterReturnLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출 - 정상 수행 시 호출");
	}

	@Around(value = "execution(* com.company.app.Product.getInfo())")
	public void aroundLog(ProceedingJoinPoint pjp) {
		System.out.println("[공통로그] 비즈니스 로직 수행 전 호출");

		// getInfo를 호출하는 구문 : 실제 수행할 메소드를 호출하는 부분(반드시 필요)
		try {
			pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출");

	}
}
