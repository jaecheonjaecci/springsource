package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BookDTO;
import com.company.service.BookService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/book/*")
public class BookController {

	@Autowired
	private BookService service;

	// insert.jsp 보여주는 컨트롤러 생성
	@GetMapping("/insert")
	public void insertGet() {
		log.info("insert.jsp 요청");
	}

	// 새로운 도서 입력
	//@ResponseBody //리턴하는 값이 jsp명이 아닌 데이터 그 자체로 인식되게끔 함
	@PostMapping("/insert")
	public ResponseEntity<String> insertPost(@RequestBody BookDTO insertDto) {
		log.info("도서 입력 요청 " + insertDto);

		//Ajax 방식은 화면을 움직이지 않기때문에 페이지 이동을 지정하는 것은 의미가 없음
		try {
			if (service.insert(insertDto)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			//상태코드와 데이터 같이 보내기
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);

		}
		//상태코드와 데이터 같이 보내기
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	// list 보여주기
	@GetMapping("/list")
	public void list() {
		log.info("전체 리스트 요청 ");
		//jsp 찾는데 사용
		
	}
	//@GetMapping({"/read","/modify"}) 이렇게 잡고, void를 하는 경우
	//주소가 바로 경로가 됨 ex) /book/modify.jsp
	@GetMapping({"/read","/modify"})
	public void readGet(String code, Model model) {
		log.info("read 페이지 요청 "+code);
		
		BookDTO dto = service.getRow(code);
		model.addAttribute("dto",dto);
		
		
	}
	//book/modify + post
	//수정이 완료된 후 내용보기
	@PostMapping("/update")
	public String modifyPost(BookDTO updateDto, RedirectAttributes rttr) {
		log.info("도서 수정 요청 "+updateDto);
		
		service.modify(updateDto);
		
		//read로 넘길 때 code를 같이 보내야 함
		//read는 code를 받겠다라고 선언해 놓았기 때문에 안 넘기면 500에러 발생
		rttr.addAttribute("code", updateDto.getCode());
		return "redirect:/book/read";
	}
	
	
	
	
	//book/remove
	@GetMapping("/remove")
	public String removeGet(String code) {
		log.info("도서 삭제 요청 " +code);
		
		service.remove(code);
		
		return "redirect:/book/list";
	}
	
	// /book/

}














