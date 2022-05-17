package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class CommonControllor {
	
	@GetMapping("/")
	public String home() {
		log.info("홈...");
		return "index";
	}
	

}
