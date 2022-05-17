package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoardDTO;
import com.company.persistence.BoardDAO;

@Service("boardService") //객체를 만들어서 여기에 사용할 것이라는 뜻
public class BoardServiceImpl implements BoardService {

	@Autowired // 만들어 놓은 객체를 여기에 사용할 것이라는 뜻
	private BoardDAO dao;
	
	@Override
	public boolean insertBoard(BoardDTO insertDto) {
		// TODO Auto-generated method stub
		return dao.insert(insertDto);
	}

	@Override
	public boolean deletetBoard(int bno) {
		// TODO Auto-generated method stub
		return dao.delete(bno);
	}

	@Override
	public BoardDTO getRow(int bno) {
		// TODO Auto-generated method stub
		return dao.getRow(bno);
	}

	@Override
	public List<BoardDTO> getRows() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public boolean updateBoard(BoardDTO updateBoardDTO) {
		// TODO Auto-generated method stub
		return dao.update(updateBoardDTO);
	}

}
