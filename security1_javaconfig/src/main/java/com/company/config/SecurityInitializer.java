package com.company.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	
	//내부적으로 DelegationgFilterProxy를 스프링에 등록시킴
	//클래스를 만드는 거 자체만으로 아래 코드를 한것과 동일함
	//	<filter>
	//	<filter-name>springSecurityFilterChain</filter-name>
	//	<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	//</filter>
	//<filter-mapping>
	//	<filter-name>springSecurityFilterChain</filter-name>
	//	<url-pattern>/*</url-pattern>
	//</filter-mapping>
}
