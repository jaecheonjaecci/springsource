package com.company.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.company.domain.BoardDTO;

public class BoardRowMapper implements RowMapper<BoardDTO> {
	//T,E,K,V => 객체 타입을 작성하면 됨 => ex)Integer,String,BoardDTO...

	@Override
	//rs에 있는 데이터를 받아 결과를 boardDto를 이용해서 담겠다
	//행하나를 어디에 담을지만 작성해주면 됨
	public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardDTO dto = new BoardDTO();
		dto.setBno(rs.getInt("bno"));
		dto.setTitle(rs.getString("title"));
		dto.setContent(rs.getString("content"));
		dto.setWriter(rs.getString("writer"));
		dto.setRegdate(rs.getDate("regdate"));
		dto.setUpdatedate(rs.getDate("updatedate"));
		return dto;
	}

}
