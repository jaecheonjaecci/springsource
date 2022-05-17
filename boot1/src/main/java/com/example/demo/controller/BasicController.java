package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BasicController {
	
	@GetMapping("/main")
	public void main() {
		log.info("메인 요청");
	}
}
