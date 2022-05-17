package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.MemberDTO;
import com.company.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/register/*")
@Log4j2
//회원등록에 관련된 controller	
public class RegisterController {
	@Autowired
	private MemberService service;

	// registGet()
	@GetMapping("/step1")
	public void registGet() {
		log.info("/step1 요청");
	}

	@PostMapping("/step2")
	public String step2(boolean agree, RedirectAttributes rttr) {
		log.info("/step2 요청");

		if (!agree) {
			// 약관동의 여부가 agree에서 true/false로 받을 수 있고,
			// 약관을 동의하지 않으면 step1 페이지 보여주기 => return "/register/step1";, 이렇게하면 주소와 보여주는 페이지가
			// 다름

			// 약관동의를 하지 않았기 때문에 원래 페이지로 돌아왔다고 알려주기 위함
			rttr.addFlashAttribute("check", "false");

			// 아예 step1 페이지를 응답하는 메소드를 부르기, 이렇게하면 주소와 보여주는 페이지가 같음
			return "redirect:/register/step1";
		}
		// 약관동의를 하면 step2 보여주기
		return "/register/step2";

	}

	// 중복아이디 검사
	@ResponseBody // 리턴하는 값이 주소가 아닌 일반 데이터인 경우 사용
	@PostMapping("/checkId")
	public String IdCheck(String userid) {
		log.info("중복아이디검사 " + userid);

		// -> remote에서 checkId로 userid값을 보내주고
		// checkId에서는 service를 호출해서 db작업을 시행해 중복되는 아이디인지 검사를 해줌
		if (service.dupId(userid) != null) {
			System.out.println("아이디중복됨");
			return "false";
			// 이렇게 false로 넘기면 register.js에서 remote가 일을 해줌
		}
		return "true";
	}

	@PostMapping("/step3")
	public String step2Post(MemberDTO memberDTO) {
		log.info("회원가입요청 " + memberDTO);

		try {
			if (!service.register(memberDTO)) {
				// insert가 성공하지 못했다면 회원가입 페이지로 이동
				return "/register/step2";
			}
		} catch (Exception e) {
			return "/register/step2";

		}
		// 성공한다면 로그인 페이지로 연결 : 이렇게 하면 주소줄과 보여주는 페이지가 일치
		return "redirect:/member/signin";

	}
	// http://localhost:8080/register/step2 + get
	// http://localhost:8080/register/step3 + get
	// 이렇게 다이렉트로 접근 시 405 에러가 뜨는 것을 막는 방법
	// 들어오면 안되는 경로로 접근 시 대표페이지로 주소를 리턴해주면 됨

	// 중괄호에 넣으면 하나의 메소드가 여러개의 주소를 처리가능함
	@GetMapping(value = { "/step2", "/step3" })
	public String handleGet() {
		log.info("/step2,/step3 직접요청");
		return "redirect:/"; // 인덱스 주소를 이렇게 표현함

	}

}
