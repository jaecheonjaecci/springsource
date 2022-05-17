package com.company.app;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BookDTO;
import com.company.service.BookService;

public class BookClient {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		//서비스 호출
		//BookService service = new BookServiceLmpl();
		//"bookServiceImpl" 이름으로 되어진 객체를 찾아서 service에 넣어달라는 뜻
		BookService service = (BookService) ctx.getBean("bookServiceImpl");
		
		//새로운 도서 입력
//		BookDTO insertDto = new BookDTO("1009","잠","베르나르",15000);
//		System.out.println(service.insertBook(insertDto)?"입력성공":"입력실패");
		
		//도서 정보 변경
//		System.out.println(service.updateBook("1006",40000)?"수정성공":"수정실패");
		
		//도서 정보 삭제
//		System.out.println(service.deleteBook("1007")?"삭제성공":"삭제실패");
		
		
		//전체리스트 결과 호출
		List<BookDTO> list = service.getList();
		
		System.out.println("코드       제목        작가       가격");
		System.out.println("===============================");
		
		for(BookDTO book : list) {
			System.out.print(book.getCode()+"\t");
			System.out.print(book.getTitle()+"\t");
			System.out.print(book.getWriter()+"\t");
			System.out.print(book.getPrice()+"\n");
		}
		
	}
}
