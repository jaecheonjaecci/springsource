package com.company.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.company.handler.CustomAccessDeniedHandler;
import com.company.handler.CustomLoginSuccessHandler;
import com.company.service.CustomUserDetailService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		//리턴타입은 부모타입으로 설정함
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService getUserDetailsService() {
		//리턴타입은 부모타입으로 설정함
		return new CustomUserDetailService();
	}
	
	@Bean
	public AccessDeniedHandler getAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	@Bean
	public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public PersistentTokenRepository persistentToken() {
		
		JdbcTokenRepositoryImpl resp = new JdbcTokenRepositoryImpl();
		resp.setDataSource(dataSource);
		return resp;
	}
	
	// configure == <security:http> 같은 의미
	protected void configure(HttpSecurity http) throws Exception {
		
		//<security:access-denied-handler ref="customAccessDeniedHandler"/>
		http.exceptionHandling(ex -> ex.accessDeniedHandler(getAccessDeniedHandler()));
		
		//<security:form-login login-page="/login" authentication-failure-url="/login-error" 
		//authentication-success-handler-ref="customLoginSuccessHandler"/>
		http.formLogin()
			.loginPage("/login")
			.failureUrl("/login-error")
			.successHandler(getAuthenticationSuccessHandler());
		
		//<security:logout logout-url="/logout" invalidate-session="true" delete-cookies="" logout-success-url="/"/>
		http.logout()
			.logoutUrl("/logout")
			.invalidateHttpSession(true)
			.deleteCookies("remember-me","JSESSION_ID") //JSESSION_ID : 서버가 구현하는 쿠키
			.logoutSuccessUrl("/");
		
		//<security:intercept-url pattern="/user-page" access="hasRole('ROLE_USER')"/>
		//<security:intercept-url pattern="/admin-page" access="hasRole('ROLE_ADMIN')"/>
		http.authorizeRequests().antMatchers("/user-page").access("hasRole('ROLE_USER')")
								.antMatchers("/admin-page").access("hasRole('ROLE_ADMIN')")
								.antMatchers("/login").permitAll(); //로그인창은 모두에게 오픈
	
		//<security:remember-me data-source-ref="ds" token-validity-seconds="604800"/>
		http.rememberMe()
			.tokenRepository(persistentToken())
			//시간은 개발자 선택
			.tokenValiditySeconds(604800);
	
	}

//	<security:authentication-manager>
//	<security:authentication-provider user-service-ref="customUserDetailService">
//		<security:password-encoder ref="bCryptPasswordEncoder"/>	
//	</security:authentication-provider>
//	</security:authentication-manager>
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(getUserDetailsService())
			.passwordEncoder(getPasswordEncoder());
	}
	
	
	
}


