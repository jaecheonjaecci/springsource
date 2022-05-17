package com.company.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
   public static void main(String[] args) {
      System.out.println("========== 스프링 컨테이너 구동 전 =========");
      
      //new ClassPathXmlApplicationContext() =>	말 그대로 resource에 있는 클래스 파일을 읽어오겠다는 뜻
      ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
      
      System.out.println("========== 스프링 컨테이너 구동 =========");

      // 스프링 컨테이너로부터 필요한 객체를 요청 : 괄호안에 들어가는 것은 객체를 생성할 아이디
      MessageBean msg = (MessageBean)ctx.getBean("ko");
      msg.sayHello("홍길동");
   }
}