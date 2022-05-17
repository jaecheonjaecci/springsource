package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BookDTO;

public interface BookService {
	public List<BookDTO> getList();
	public boolean insert(BookDTO insertDto);
	public BookDTO getRow(String code);
	public boolean remove(String code);
	public boolean modify(BookDTO updateDto);

}
