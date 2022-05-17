package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.BookDTO;
import com.company.service.BookService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/book/*")
public class BookRestController {
	
	@Autowired
	private BookService service;

	
	@PostMapping("/insert-rest")
	public String insertPost(@RequestBody BookDTO insertDto) {
		log.info("도서 입력 요청 " + insertDto);

		//Ajax 방식은 화면을 움직이지 않기때문에 페이지 이동을 지정하는 것은 의미가 없음
		try {
			if (service.insert(insertDto)) {
				return "success";
			}
		} catch (Exception e) {
			//상태코드와 데이터 같이 보내기
			return "fail";

		}
		//상태코드와 데이터 같이 보내기
		return "fail";
	}
	
	//json 타입으로 보내겠다는 뜻
	@GetMapping(value="/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	//(@PathVariable("code") String code) => path에 들어오는 값을 code에 넣어달라는 뜻
	public ResponseEntity<BookDTO> row(@PathVariable("code") String code){
		
		log.info("책 하나 가져오기 "+code);
		
		BookDTO dto = service.getRow(code);
		
		return new ResponseEntity<BookDTO>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value="/rest-list",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BookDTO>> getList(){
		log.info("rest 방식 list 요청");
		return new ResponseEntity<List<BookDTO>>(service.getList(),HttpStatus.OK);
	}
	
	// /book/{code} + delete
	@DeleteMapping("/{code}")
	public ResponseEntity<String> delete(@PathVariable("code") String code){
		
		log.info("rest 삭제 "+code);
		
		if(service.remove(code)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
			
		} else {
			return new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	///book/update +수정내용+ put(patch)
	@PutMapping("/update")
	public ResponseEntity<String> modify(@RequestBody BookDTO updateDto){
		                                  //json으로 넘어오는 자료를 DTO와 mapping하기 위함
		log.info("rest 수정 "+updateDto);
		
		if(service.modify(updateDto)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
			
		}else {
			
			return new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		}
	}
	
	
}





















