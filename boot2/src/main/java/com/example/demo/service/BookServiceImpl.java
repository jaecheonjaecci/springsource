package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BookDTO;
import com.example.demo.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper mapper;

	@Override
	public List<BookDTO> getList() {
		return mapper.list();
	}

	@Override
	public boolean insert(BookDTO insertDto) {
		return mapper.insert(insertDto) > 0 ? true : false;
	}

	@Override
	public BookDTO getRow(String code) {
		return mapper.read(code);
	}

	@Override
	public boolean remove(String code) {
		return mapper.delete(code) > 0 ? true : false;
	}

	@Override
	public boolean modify(BookDTO updateDto) {
		return mapper.update(updateDto) > 0 ? true : false;
	}

}
