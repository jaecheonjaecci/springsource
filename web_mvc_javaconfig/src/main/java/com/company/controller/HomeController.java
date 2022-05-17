package com.company.controller;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Controller
public class HomeController {
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@GetMapping("/doA")
	public String doA(RedirectAttributes rttr) {
		log.info("/doA 요청");
		
		//RedirectAttributes : "sendRedirect"로 움직일 때 값을 전달하는 객체
		//이렇게 보내면 주소줄로 값을 보낼 수 있음
		rttr.addAttribute("age", 20);
		
		//flash는 주소줄로 데이터를 보낼 수 없음
		//but 임시 session.Attribute에 담긴 것이라서 데이터를 불러올 수 있음
		//but 일반 session에 담듯이 다른 모든 페이지에서 값을 끌고 다닐 수 있는 것은 아님
		rttr.addFlashAttribute("name", "홍길동");
		
		
		//redirect로 이동방법 설정 시 미리 지정되어 있는 경로만 사용할 수 있음
		return "redirect:/doB";
	}
	@GetMapping("/doB")
	public void doB() {
		log.info("/doB 요청");
		
	}
	
	@GetMapping("/doC")
	public void doC() {
		log.info("/doC 요청");
		
	}
	
}



















