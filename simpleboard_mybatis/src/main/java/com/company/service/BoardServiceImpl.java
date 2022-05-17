package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoardDTO;
import com.company.mapper.BoardMapper;


@Service("boardService") //객체를 만들어서 여기에 사용할 것이라는 뜻
public class BoardServiceImpl implements BoardService {

	@Autowired // 만들어 놓은 객체를 여기에 사용할 것이라는 뜻
	private BoardMapper mapper;
	
	
	@Override
	public boolean insertBoard(BoardDTO insertDto) {
		// TODO Auto-generated method stub
		return mapper.insert(insertDto)>0?true:false;
	}

	@Override
	public boolean deletetBoard(int bno) {
		// TODO Auto-generated method stub
		return mapper.delete(bno)>0?true:false;
	}

	@Override
	public BoardDTO getRow(int bno) {
		// TODO Auto-generated method stub
		return mapper.read(bno);
	}

	@Override
	public List<BoardDTO> getRows() {
		// TODO Auto-generated method stub
		return mapper.list();
	}

	@Override
	public boolean updateBoard(BoardDTO updateBoardDTO) {
		// TODO Auto-generated method stub
		return mapper.update(updateBoardDTO)>0?true:false;
	}

}
