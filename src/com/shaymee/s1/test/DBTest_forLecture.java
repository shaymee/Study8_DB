package com.shaymee.s1.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.shaymee.s1.util.DBConnect;

public class DBTest_forLecture {
	
	private DBConnect dbConnect; // private으로 변수선언
	
	public DBTest_forLecture() { //생성자를 이용해 DBConnect의 객체 생성
		dbConnect = new DBConnect();
	}
	
	public void getCount() {		/** getCount() */
		
			Connection con = null;
			PreparedStatement st = null;
			ResultSet rs = null;
		
			try {
			
			con = dbConnect.getConnection();
		
			String sql = "SELECT COUNT(DEPARTMENT_ID) as 부서숫자 FROM DEPARTMENTS";
			
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			
			if(rs.next()) { // rs.next() --> 한줄은 무조건 읽어야함
				System.out.println(rs.getString("부서숫자"));
			}
			
			} catch(Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			} finally {

				dbConnect.disConnect(rs, st, con);		
		}
		
	}
	
	

	public void getList() { 	  /** getList() */
			
			Connection con = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			
			try {
			
			con = dbConnect.getConnection();
				
			//4) SQL문 생성
			String sql = "SELECT * FROM DEPARTMENTS";// 데이터베이스에서 활용할 query문을 쓰는것 **Java에서는 ;(세미콜론) 쓰지마라. jdbc가 알아서 붙여줌
			
			//5) SQL문 미리 보내기(나중에 보내면 오류가 발생함(옛날방식))
			st /* PreparedStatement를 통해 보낸거에 대해서 받아줌 */ = con.prepareStatement(sql);
			
			//7) 최종 전송 후 결과 처리
			rs /* rs에는 Oracle의 최종결과물이 담겨져있음 */ = st.executeQuery(); 
			
			while(rs.next()) {
				System.out.print(rs.getInt("DEPARTMENT_ID")+"\t"); // "\t"는 tab만큼 띄우는거 
				System.out.print(rs.getString("DEPARTMENT_NAME")+"\t");
				System.out.println(rs.getInt("MANAGER_ID"));
				System.out.println("------------------");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbConnect.disConnect(rs, st, con);	
		}
		
		System.out.println("--- Connect Test Finish ---");
	}
	
	public void getOne() {	/** getOne() */
			
			Connection con = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			
		
			try {
				
			con = dbConnect.getConnection();
	
			int id = 40;
			//4)sql문 생성
			String sql = "SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?";
			
			//5)미리 전송
			st = con.prepareStatement(sql); // ?값이 오면 미리 실행해야지
			
			//6) ?값을 설정 : setXXX(?의 index번호, ?에 들어갈 실제값). 여기서 물음표에 들어가야하는 값은 int니까 setInt()
			// Oracle은 시작번호로 1번을 가짐. ?값은 Oracle로 가기 때문에 Oracle의 시작번호를 기준으로 삼아야함
			st.setInt(1, id);
			
			//7)최종전송 후 결과 처리
			rs = st.executeQuery();
			
			if(rs.next()) { //rs.next() 호출 했는데 있으면 true, 없으면 false
				System.out.println(rs.getString("DEPARTMENT_NAME"));				
			} else {
				System.out.println("그런 번호는 없다");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			
			dbConnect.disConnect(rs, st, con);	
		}
		
		
	}
}
