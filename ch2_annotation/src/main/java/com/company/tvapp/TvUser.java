package com.company.tvapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TvUser {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		TV tv=(TV) ctx.getBean("samsungTv");
	
		tv.turnOn();
		tv.soundUp();
		tv.soundDown();
		tv.turnOff();

	}

}
