package com.shaymee.s1.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTest {

	public void getCount() {																/** getCount() */
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
	
	
	
	public void getList() { 																	/** getList() */
		System.out.println("--- Connect Test Start ---");
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
				
		try {
			//2) 드라이버 로딩 작업(드라이버를 메모리에 로딩)
			Class.forName(driver); // Class.forName(String str) 문자열을 클래스명으로 만들어주는 메소드. 객체화 시켜주는 작업?
			System.out.println("Driver 로딩 성공");
			
			//3) DB 접속
			Connection con = DriverManager.getConnection(url, user, pw); // 얘를 Socket이라고 보면 됨. 얠 통해서 뭔갈 보내고 얠 통해서 받음
			System.out.println("접속 성공");
			System.out.println(con);
			
			//4) SQL문 생성
			String sql = "SELECT * FROM DEPARTMENTS";// 데이터베이스에서 활용할 query문을 쓰는것 **Java에서는 ;(세미콜론) 쓰지마라. jdbc가 알아서 붙여줌
			
			//5) SQL문 미리 보내기(나중에 보내면 오류가 발생함(옛날방식))
			PreparedStatement st /* PreparedStatement를 통해 보낸거에 대해서 받아줌 */ = con.prepareStatement(sql);
			
			//7) 최종 전송 후 결과 처리
			ResultSet rs /* rs에는 Oracle의 최종결과물이 담겨져있음 */ = st.executeQuery(); 
			
			while(rs.next()) {
				System.out.print(rs.getInt("DEPARTMENT_ID")+"\t"); // "\t"는 tab만큼 띄우는거 
				System.out.print(rs.getString("DEPARTMENT_NAME")+"\t");
				System.out.println(rs.getInt("MANAGER_ID"));
				System.out.println("------------------");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("--- Connect Test Finish ---");
	}
	
	public void getOne() {																				/** getOne() */
		
		String user = "user02";
		String password = "user02";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//3)Connection
			con = DriverManager.getConnection(url, user, password);
			System.out.println("접속 성공");
			
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
	
	
	
}
