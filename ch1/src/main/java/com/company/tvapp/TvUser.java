package com.company.tvapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TvUser {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		TV tv= (TV)ctx.getBean("samsung");
		
		//결합력을 낮추는 방식 : 다형성
//		TV tv = new SamsungTv();
		tv.turnOn();
		tv.soundUp();
		tv.soundDown();
		tv.turnOff();
		
		TV tv1 = (TV) ctx.getBean("samsung");
		
		System.out.println(tv==tv1 ? "객체 동등" : "객체 다름");
	}

}
