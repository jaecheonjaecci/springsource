package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.domain.BoardDTO;
import com.company.domain.Criteria;

public interface BoardMapper {
	public int insert(BoardDTO insertDto);
	
	//페이지마다 보여줄 데이터의 갯수와 페이지번호를 넘기기 위해 Criteria를 넘겨줌
	public List<BoardDTO> listAll(Criteria cri);
	
	public BoardDTO read(int bno);
	public int update(BoardDTO updateDto);
	public int delete(int bno);
	
	public int totalCnt(Criteria cri);
	public int updateReplyCnt(@Param("bno") int bno, @Param("amount") int amount);
}
