package com.company.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//현재 클래스가 테스트 클래스고 테스트 코드가 스프링을 실행
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
					   "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class MemberTests {
	
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private DataSource ds;
	
	//테스트 메소드, @Test 어노테이션 사용 / public, 파라미터 없고, void 타입
	@Test
	public void test() {
		String sql = "insert into spring_member(userid,userpw,username) values(?,?,?)";
		
		for(int i=0;i<100;i++) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(2, encoder.encode("pw"+i));
				
				if(i<80) {
					pstmt.setString(1, "user"+i); //user0 ~ user79
					pstmt.setString(3, "일반사용자"+i);
					
				}else if(i<90) {
					pstmt.setString(1, "manager"+i); //manager80 ~ manager89
					pstmt.setString(3, "운영자"+i);
					
				}else {
					pstmt.setString(1, "admin"+i); //admin90 ~ admin99
					pstmt.setString(3, "관리자"+i);
					
				}
				pstmt.executeUpdate();
				
						
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
