package com.shaymee.s1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect_forEmployee {

	public Connection getConnection() throws Exception {
	
	//1)접속정보 기재
	String user = "user02";
	String password = "user02";
	
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	//2)Database 접속
	Connection con = DriverManager.getConnection(url, user, password);
	System.out.println("*** Database에 접속 성공 ***");
	
	return con;
	
	}
	
	
	public void disConnection(ResultSet rs, PreparedStatement st, Connection con) {
		
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
