package com.company.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.*;

@Repository // = @Component 객체를 생성해달라는 뜻
public class BookDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// insert
	public boolean insert(BookDTO insertDto) {

		String sql = "insert into bookTBL values(?,?,?,?)";

		int result = jdbcTemplate.update(sql, insertDto.getCode(), insertDto.getTitle(), insertDto.getWriter(),
				insertDto.getPrice());
		return result > 0 ? true : false;

	}

	// delete
	public boolean delete(String code) {

		String sql = "delete from bookTBL where code=?";

		int result = jdbcTemplate.update(sql, code);

		return result > 0 ? true : false;
	}

	// update
	public boolean update(String code, int price) {

		String sql = "update bookTBL set price=? where code=?";
		int result = jdbcTemplate.update(sql, price, code);

		return result > 0 ? true : false;

	}

	// select
	public List<BookDTO> list() {

		String sql = "select * from bookTBL";
		return jdbcTemplate.query(sql, new BookRowMapper());

	}

	// search
	public List<BookDTO> search(String criteria, String keyword) {

		String sql = "select * from bookTBL where " + criteria + "=?";
		return jdbcTemplate.query(sql, new BookRowMapper(), keyword);

	}

}
