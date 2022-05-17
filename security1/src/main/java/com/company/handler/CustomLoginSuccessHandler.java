package com.company.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	//인증이 성공하면 동작하기
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			// authentication : 인증 성공시 각종 정보를 담음
			Authentication authentication) throws IOException, ServletException {
		
		log.info("로그인 성공");
		
		//로그인 성공 시 role == admin => admin-page로 이동
		//로그인 성공 시 role == user => user-page로 이동
		
		//한 사람당 여러개의 롤을 가질 수 있어서 list로 받음
		List<String> roleNames = new ArrayList<String>();
		authentication.getAuthorities().forEach(authority ->{
			roleNames.add(authority.getAuthority());
		});
		
		log.info("롤 네임즈"+roleNames);
		
		if(roleNames.contains("ROLE_ADMIN")){
			response.sendRedirect("/admin-page");
			return;
		}
		if(roleNames.contains("ROLE_USER")) {
			response.sendRedirect("/user-page");
			return;
		}
		//롤이 없는 경우 인덱스 페이지로
		response.sendRedirect("/");
	}

}
