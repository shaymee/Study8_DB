package com.shaymee.s1.location;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LocationDAO { //DataAccessObject 데이터객체에 접근하는 놈
	
	public void getOne(int location_id) {
		
		System.out.println("= getOne 메소드 Start =");
		Scanner sc = new Scanner(System.in);
		
		String user = "user02";
		String password  = "user02";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("DB 접속 성공");
			
			String sql = "SELECT * FROM LOCATIONS WHERE LOCATION_ID = ?";
			
			st = con.prepareStatement(sql);
			
			st.setInt(1, location_id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("CITY")); // Oracle에서 실제 출력되서 보이는 그대로의 이름이어야 함 Ex) DEPARTMENT_ID as DI 면 rs.getString("DI")로 입력해야됨
														  // Java는 ResultSet에서 Data를 가져오는 것. 
			} else {
				System.out.println("그런 CITY 없음");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	
	public void getCount() {
		//DEPARTMENTS 테이블 조회
		//SELECT COUNT(DEPARTMENT_ID) FROM DEPARTMENTS;를 Java에서 출력하기 -->27이 나와야 함
		
		String user = "user02";
		String password = "user02";
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			con=DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT COUNT(DEPARTMENT_ID) as 부서숫자 FROM DEPARTMENTS";
			
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			
			if(rs.next()) { // rs.next() --> 한줄은 무조건 읽어야함
				System.out.println(rs.getString("부서숫자"));
			}
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
	public void getList() {
		//LOCATIONS Table을 조회(Select)해서 출력
		System.out.println("= getList 메소드 Start =");
		
		//1) 접속정보 기재
		String user = "user02";
		String password = "user02";
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		
		try {
			
			//2) Driver를 메모리에 로딩
			Class.forName(driver); // () 안에 있는 이름의 객체가 있을테니까 이 클래스를 찾아서 객체를 생성해달라는 메서드 
			// Class.forName()은 요즘 안써도 되는걸로 바꼈음.			
			
			System.out.println("Driver 로딩 성공");
			
			//3) DB Connection
			con = DriverManager.getConnection(url, user, password);
			System.out.println("DB 접속 성공");
			
			//4) SQL문 생성
			String sql = "SELECT * FROM LOCATIONS";
			
			//5) 미리 전송
			st = con.prepareStatement(sql);
			
			//6) 최종 전송 후 결과 처리
			rs = st.executeQuery();
			
			System.out.println("LOCATION_ID"+"\t"+"STREET_ADDRESS"+"\t"+"POSTAL_CODE"+"\t"+"CITY"+"\t"+"STATE_PROVINCE"+"/t"+"COUNTRY_ID");
			System.out.println("----------------------------------------------------------------------------");
			while(rs.next()) { // rs.next() --> 한줄 읽고 다음 줄로 내려감. 내려가서 한줄이 또 있으면 true. 어쨌든 ResultSet에 있는걸 읽는 메서드임
				
				//어떤 컬럼을 먼저 꺼내오든 순서는 상관X. 오타없이 정확히만 입력하면 됨.
				System.out.print(rs.getInt("LOCATION_ID")+"\t");
				System.out.print(rs.getString("STREET_ADDRESS")+"\t");
				System.out.print(rs.getString(3)+"\t"); // 인덱스번호 3번 -> "POSTAL_CODE"
				System.out.print(rs.getString("CITY")+"\t");
				System.out.print(rs.getString(5)+"\t"); // 인덱스번호 5번 -> "STATE_PROVINCE"
				System.out.println(rs.getString("COUNTRY_ID"));
				System.out.println("-------------------------------------------------------------------------------");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { //연결된 자원을 해제할 때 finally를 많이 씀. finally는 exception이 있든 없든 무조건 실행됨.
			try {	//끊어주는 것은 실행된 것들의 역순으로
				
				rs.close();
				st.close();
				con.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	
	
}
