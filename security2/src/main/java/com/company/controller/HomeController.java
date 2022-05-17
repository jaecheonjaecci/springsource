package com.company.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("Welcome home!");

		return "home";
	}

	@GetMapping("/all")
	public void all() {
		log.info("올 페이지");
	}
	
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	@GetMapping("/member")

	public void member() {
		log.info("멤버 페이지");

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public void admin() {
		log.info("어드민 페이지");

	}
	
	//Authentication 객체에 담긴 정보 확인
	@ResponseBody
	@GetMapping("/auth")
	public Authentication auth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	//접근 권한이 없는 페이지에 접근할 경우 
	@GetMapping("/accessError")
	public void accessError() {
		
	}
	

}



























