package com.company.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.service.MemberService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member/*")
@Log4j2
public class MemberController {
//로그인, 로그아웃, 회원탈퇴, 회원정보 수정을 하는 controller	

	@Autowired
	private MemberService service;

	// 로그인페이지 요청
	@GetMapping("/signin")
	public void signin() {
		log.info("로그인 페이지 요청");
	}

	// 로그인
	@PostMapping("/signin")
	public String loginPost(LoginDTO loginDTO, HttpSession session) {
		log.info("로그인 요청 " + loginDTO);

		loginDTO = service.login(loginDTO);

		session.setAttribute("loginDTO", loginDTO);

		// index를 호출
		return "redirect:/";
	}

	// 로그아웃
	// get or post는 요청이 어디서 들어오느냐에 따라 다름
	// 여기는 a 태그를 타고 들어왔기 때문에 get 방식임
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("로그아웃 요청");

		// 전체세션를 날려버리는 것 : session.invalidate();
		// 로그인 세션을 날리기 : session.removeAttribute("loginDTO");
		session.invalidate();
		return "redirect:/";
	}

	// 비밀번호 변경페이지 보여주기
	// get or post는 요청이 어디서 들어오느냐에 따라 다름
	// 여기는 a 태그를 타고 들어왔기 때문에 get 방식임
	@GetMapping("/changePwd")
	public void changePwd() {
		log.info("비밀번호 변경 페이지 요청");
	}

	@PostMapping("/changePwd")
	public String changePwdPost(ChangeDTO changeDTO, HttpSession session) {
		log.info("비밀번호 변경 요청 " + changeDTO);

		// 비밀번호 변경 요청
		// 1. userid 가져오기
		LoginDTO loginDTO = (LoginDTO) session.getAttribute("loginDTO");
		changeDTO.setUserid(loginDTO.getUserid());

		if (service.changePwd(changeDTO)) {
			// 비밀번호 변경 시 기존 세션 해제, 로그인 페이지로 연결
			session.invalidate();
			return "redirect:/member/signin";

		} else {
			// 비밀번호 변경 실패 시 다시 changePwd 페이지로 연결
			return "redirect:/member/changePwd";
		}

	}

	// 회원 탈퇴 폼 보여주기
	@GetMapping("/leave")
	public void leaveGet() {
		log.info("탈퇴 페이지 요청");
	}

	// 회원탈퇴하기
	@PostMapping("/leave")
	public String leavePost(LoginDTO leaveDTO, HttpSession session) {
		log.info("탈퇴 요청하기 " + leaveDTO);

		if (service.leave(leaveDTO)) {
			// 세션해제
			session.invalidate();
			return "redirect:/";
		}
		return "redirect:/member/leave";

	}

}
