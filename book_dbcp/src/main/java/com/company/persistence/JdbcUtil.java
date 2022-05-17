package com.company.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JdbcUtil {
//이 부분을 hikari로 사용하므로 여기서 사용하지 않음
//	// 드라이버 로드
//	static {
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// connection
//	public static Connection getConnection() {
//		try {
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "c##java";
//			String password = "12345";
//			Connection con = DriverManager.getConnection(url, user, password);
//			
//			//자바 응용프로그램인 autocommit 상태
//			//autocommit을 false로 바꿔주는 부분
//			con.setAutoCommit(false);
//			return con;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//
//	}
	
	//autocommit을 막았기 때문에 필요한 작업
	//commit
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//rollback
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	// 자원 해제
	public static void close(Connection con) {
		if (con != null) {
			try {
				con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
