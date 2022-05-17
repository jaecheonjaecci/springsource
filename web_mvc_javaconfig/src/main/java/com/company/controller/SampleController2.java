package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller // 객체 생성을 위한 annotation
public class SampleController2 {


//	@RequestMapping("/member/basic")
	@GetMapping("/member/basic")
	public void basic() {
		log.info("/member/basic 요청...........");

		// view resolver에게 => /member/basic 주소가 넘어감
	}

	// get 방식에만 응답
//	@RequestMapping(value = "/member/login", method = RequestMethod.GET) // /member/login + get
	@GetMapping("/member/login")
	public String login() {
		log.info("/member/login 요청...........");
		return "login";
		// view resolver에게 => /member/login 주소가 넘어감
	}

	// post 방식에 응답 => forward 방식으로 이동
//	@RequestMapping(value = "/member/login", method = RequestMethod.POST) // /member/login +post
	@PostMapping("/member/login")
	public String loginPost() {
		log.info("/member/login Post 요청");
		return "/sample/basic";

	}
//	@RequestMapping(value = "/admin/info", method = RequestMethod.GET)
	@GetMapping("/admin/info")
	public void method1() {
		log.info("/admin/info 요청...");
		// void인 경우는 현재 주소가 그대로 view resolver에게 넘어감
	}
//	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@GetMapping("/admin")
	public String method2() {
		log.info("/admin 요청...");
		return "admin";
	}

}
