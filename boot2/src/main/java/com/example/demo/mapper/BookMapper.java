package com.example.demo.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.BookDTO;

@Mapper
public interface BookMapper {
	public List<BookDTO> list();
	public BookDTO read(String code);
	public int insert(BookDTO insertDto);
	public int delete(String code);
	public int update(BookDTO updateDto);

}
