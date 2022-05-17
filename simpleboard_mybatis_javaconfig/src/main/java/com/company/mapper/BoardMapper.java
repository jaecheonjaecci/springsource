package com.company.mapper;

import java.util.List;

import com.company.domain.BoardDTO;

public interface BoardMapper {
	public int insert(BoardDTO insertDto);
	public int delete(int bno);
	public BoardDTO read(int bno);
	public List<BoardDTO> list();
	public int update(BoardDTO updateDto);

}
