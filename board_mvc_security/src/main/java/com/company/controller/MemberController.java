package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class MemberController {

	@GetMapping("/login")
	public String login() {
		log.info("로그인 폼 요청");
		return "/member/loginForm";
	}
	
	@GetMapping("/customLogout")
	public String logout() {
		log.info("로그아웃 폼 요청");
		return "/member/logoutForm";
	}
}
