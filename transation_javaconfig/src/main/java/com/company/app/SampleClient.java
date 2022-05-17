package com.company.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.config.TransationConfig;
import com.company.service.SampleService;

public class SampleClient {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(TransationConfig.class);
		SampleService service = (SampleService) ctx.getBean("sample");
		
		String data = "Starry\r\n"
				+"Starry night\r\n"
				+"Paint your paletter blue and gray\r\n"
				+"Look out on a summer day";
		
		service.addDate(data);

	}

}