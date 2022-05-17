package com.company.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// 접근 제한할 때 해야하는 작업이 있을 시 직접 핸들러 구현 가능
		log.info("접근제한 핸들러");
		log.info("리다이렉트....");
		
		//다시 컨트롤러로 보내기
		response.sendRedirect("/access-denied");
		
	}

}
