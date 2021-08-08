package com.shaymee.s1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {

	public Connection getConnection() throws Exception {

		//1. DB server에 접속
				//	 -- ip정보:port정보 필요 (오라클은 port번호를 1521 씀)
				//2. Login
				//   -- id, pw 정보 필요
				//3. SQL문(QUERY문) 작성해서 보내기
				//4. 결과
				
				
				//1) 접속정보 기재
				String user = "user02";
				String pw = "user02";
				//jdbc:oracle:thin:@ip:port:sid
				//database마다의 고유 id => sid
				//express edition의 경우: sid = xe
				//정식버전의 경우 : orcl
				String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; 
				String driver = "oracle.jdbc.driver.OracleDriver";
						
				
				//2) 드라이버 로딩 작업(드라이버를 메모리에 로딩)
//				Class.forName(driver); // Class.forName(String str) 문자열을 클래스명으로 만들어주는 메소드. 객체화 시켜주는 작업?
				System.out.println("Driver 로딩 성공");
					
				//3) DB 접속
				Connection con = DriverManager.getConnection(url, user, pw); // 얘를 Socket이라고 보면 됨. 얠 통해서 뭔갈 보내고 얠 통해서 받음
				System.out.println("접속 성공");
				System.out.println(con);
				
				return con;
	}
	
	
	public void disConnect(ResultSet rs,PreparedStatement st, Connection con) {
	
		try {
			
			rs.close();
			st.close();
			con.close();
			
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
	}
	
	
	
	
}
