package com.company.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.BoardDTO;

@Repository // 객체를 생성해달라는 뜻
public class BoardDAO {

	@Autowired
	//JdbcTemplate는 클래스
	private JdbcTemplate jdbcTemplate;

	// 삽입
	public boolean insert(BoardDTO insertDto) {

		String sql = "insert into spring_board(bno,title,content,writer) values(seq_board.nextval,?,?,?)";

		// 내부적으로 executeUpdate();를 호출하는 것과 같음
		// => result로 int값을 돌려줌
		// update(sql, Object...args) : 가운데 ...은 인자의 갯수가 정해지지 않았음을 의미
		int result = jdbcTemplate.update(sql, insertDto.getTitle(), insertDto.getContent(), insertDto.getWriter());

		return result > 0 ? true : false;
	}

	// 전체 조회
	public List<BoardDTO> list() {

		String spl = "select*from SPRING_BOARD";
		
		//query : query 실행결과를 목록으로 가져올 때 사용함
		return jdbcTemplate.query(spl, new BoardRowMapper());

	}

	// 특정 게시물 가져오기
	public BoardDTO getRow(int bno) {

		String sql = "select*from SPRING_BOARD where bno=?";
		
		//queryForObject는 읽어올 인자가 하나인 경우만 사용가능, pk를 불러올때 사용
		//rs에 담은 결과를 BoardRowMapper()에 넘겨줌
		return jdbcTemplate.queryForObject(sql, new BoardRowMapper());
	}

	// 수정하기
	public boolean update(BoardDTO updateDto) {

		String sql = "update spring_board set title=?,content=?,updatedate=sysdate where bno=?";
		
		//						내부적으로 executeUpdate();를 호출하는 것과 같음
		int result = jdbcTemplate.update(sql, updateDto.getTitle(), updateDto.getContent(), updateDto.getBno());

		return result > 0 ? true : false;
	}

	// 삭제하기
	public boolean delete(int bno) {

		String sql = "delete from spring_board where bno=?";
		
		//						내부적으로 executeUpdate();를 호출하는 것과 같음
		int result = jdbcTemplate.update(sql, bno);

		return result > 0 ? true : false;
	}

}
