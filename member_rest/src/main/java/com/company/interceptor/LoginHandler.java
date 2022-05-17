package com.company.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.company.domain.LoginDTO;

public class LoginHandler implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// session 정보를 확인 후 로그인 정보가 있다면 원하는 컨트롤러로 진입
		HttpSession session = request.getSession();

		LoginDTO loginDTO = (LoginDTO) session.getAttribute("loginDTO");

		if (loginDTO != null) {
			return true;
			//controller로 진입하라는 뜻
		}

		// session이 없다면 로그인 페이지로 이동
		response.sendRedirect("/member/signin");
		return false;
	}
}
