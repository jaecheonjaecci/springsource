package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookDTO;
import com.company.persistence.BookDAO;

@Service
// @Service=> BookServiceImpl 객체를 생성함 : id는 기본형태로 생성
public class BookServiceImpl implements BookService {

	@Autowired // dao에 생성된 객체를 넣어달라는 의미 => bookdao가 들어옴
	private BookDAO dao;

	@Override
	public List<BookDTO> getList() {

		return dao.list();
	}

	@Override
	public boolean insertBook(BookDTO dto) {

		return dao.insert(dto);
	}

	@Override
	public boolean updateBook(String code, int price) {
		// TODO Auto-generated method stub
		return dao.update(code,price);
	}

	@Override
	public boolean deleteBook(String code) {
		// TODO Auto-generated method stub
		return dao.delete(code);
	}

}
