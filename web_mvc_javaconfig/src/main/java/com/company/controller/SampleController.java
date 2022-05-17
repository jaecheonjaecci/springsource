package com.company.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.domain.UserDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller // 객체 생성을 위한 annotation
@RequestMapping("/sample/*") // 주소 지정시 사용
public class SampleController {

	// ModelAndView - 옛날 코드에서 리턴타입으로 사용함

	@RequestMapping("/basic")
	public void basic() {
		log.info("/basic 요청...........");

		// view resolver에게 => /sample/basic 주소가 넘어감
	}

	// get,post 두 방식에 모두 응답함
//	@RequestMapping("/login") // /sample/login
//	public String login() {
//		log.info("/login 요청...........");
//		return "login";
//		//view resolver에게 => /login 주소가 넘어감
//	}

	// get 방식에만 응답
	@RequestMapping(value = "/login", method = RequestMethod.GET) // /sample/login + get
	public String login() {
		log.info("/login 요청...........");
		return "login";
		// view resolver에게 => /login 주소가 넘어감
	}

	// post 방식에 응답 => forward 방식으로 이동
//	@RequestMapping(value = "/login", method = RequestMethod.POST) // /sample/login +post
//	public String loginPost() {
//		log.info("/login Post 요청");
//		return "/sample/basic";
//		// DispacherSevlet(view resolver)에게 => /login 주소가 넘어감
//		// view에서 나머지 주소가 붙음
//		// => /WEB-INF/views/sample/basic.jsp 이렇게 실행됨
//	}

	// post 방식에 응답 + 사용자의 입력값 가져오기
	// 1. HttpServletRequest 객체 사용하기 : 사용도가 높지 않음
	// 2. 바인딩 변수 사용하기 
	// 3. 바인딩 객체 사용하기 
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST) // /sample/login +post
//	public String loginPost(HttpServletRequest req) {
//		
//		log.info("/login Post 요청");
//		
//		log.info("userid : "+req.getParameter("userid"));
//		log.info("password : "+req.getParameter("password"));
//		
//		return "/sample/basic";
//	}
	
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST) // /sample/login +post
//	//변수명과 같은 페이지의 name을 일치시켜도 되고, RequestParam을 이용해 이름을 다시 지정해줘도 됨
//	public String loginPost(String userid, @RequestParam("pwd") String password, Model model) {
//		
//		log.info("/login Post 요청");
//		
//		log.info("userid : "+userid);
//		log.info("password : "+password);
//		
//		model.addAttribute("userid", userid); //request.setAttribute와 같은 의미
//		model.addAttribute("password", password); //request.setAttribute와 같은 의미
//		//이렇게 담으면 다음 페이지까지 값들이 유지됨
//		
//		return "/sample/basic";
//	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST) // /sample/login +post
	public String loginPost(@ModelAttribute("login") UserDTO userDto) { //userDTO에 담을 경우 다음 페이지까지 값이 유지됨
		//UserDTO는 자동으로 userDTO로 설정되어 그 이름을 사용해도 되지만 
		//modelAttribute를 사용해서 따로 이름을 설정할 수 있음
		
		log.info("/login Post 요청");
		
		log.info("userid : "+userDto.getUserid());
		log.info("password : "+userDto.getPassword());
		log.info("name : "+userDto.getName());
		
		return "/sample/basic";
	}

}
