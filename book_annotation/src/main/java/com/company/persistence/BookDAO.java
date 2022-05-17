package com.company.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.domain.*;

import static com.company.persistence.JdbcUtil.*;

@Repository // = @Component 객체를 생성해달라는 뜻 
public class BookDAO {

	//insert
	public boolean insert(BookDTO insertDto) {
		String sql = "insert into bookTBL values(?,?,?,?)";
		boolean insertFlag = false;
		
		PreparedStatement pstmt = null;
		Connection con = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, insertDto.getCode());
			pstmt.setString(2, insertDto.getTitle());
			pstmt.setString(3, insertDto.getWriter());
			pstmt.setInt(4, insertDto.getPrice());
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				insertFlag=true;
				commit(con);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return insertFlag;
		
	}
	
	//delete
	public boolean delete(String code) {
		boolean deleteFlag=false;
		PreparedStatement pstmt = null;
		Connection con = null;
		
		try {
			con=getConnection();
			String sql = "delete from bookTBL where code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				deleteFlag=true;
				commit(con);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		return deleteFlag;
	}
	
	//update
	public boolean update(String code,int price) {
		boolean updateFlag = false;
		PreparedStatement pstmt = null;
		Connection con = null;
		
		try {
			con = getConnection();
			String sql = "update bookTBL set price=? where code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,price);
			pstmt.setString(2,code);
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				updateFlag=true;
				commit(con);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateFlag;
		
	}

	//select
	public List<BookDTO> list(){
		List<BookDTO> bookList = new ArrayList<BookDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		try {
			con = getConnection();
			String sql = "select * from bookTBL order by code";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			BookDTO dto = new BookDTO();
				dto.setCode(rs.getString("code"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setPrice(rs.getInt("price"));
				

				bookList.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return bookList;
	}
	
	//search
	public List<BookDTO> search(String criteria, String keyword) {
		List<BookDTO> bookList = new ArrayList<BookDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		try {
			String sql = "select * from bookTBL where "+criteria+"=?";
			
			con = getConnection();
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				
				dto.setCode(rs.getString("code"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setPrice(rs.getInt("price"));
				
				bookList.add(dto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return bookList;
	}
	
}
