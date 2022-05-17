package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookDTO;
import com.company.mapper.BookMapper;

@Service
// @Service=> BookServiceImpl 객체를 생성함 : id는 기본형태로 생성
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper mapper;

	@Override
	public List<BookDTO> getList() {

		return mapper.list();
	}

	@Override
	public boolean insertBook(BookDTO dto) {

		return mapper.insert(dto) >0?true:false;
	}

	@Override
	public boolean updateBook(String code, int price) {
		return mapper.update(code,price)>0?true:false;
	}

	@Override
	public boolean deleteBook(String code) {
		return mapper.delete(code)>0?true:false;
	}

	@Override
	public BookDTO getRow(String code) {
		return mapper.read(code);
	}

}
