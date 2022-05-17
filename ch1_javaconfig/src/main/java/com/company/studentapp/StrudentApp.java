package com.company.studentapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StrudentApp {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		StudentInfo info = (StudentInfo) ctx.getBean("info");
		info.getStudentInfo();

	}

}
