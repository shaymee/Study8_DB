package com.shaymee.s1.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shaymee.s1.util.DBConnect_forEmployee;

public class EmployeeDAO {
	
	private DBConnect_forEmployee dbConnectt;
	
	public EmployeeDAO() { // 생성자를 통해 DBConnect 객체 생성
		
		dbConnectt = new DBConnect_forEmployee();
	
	}
	
	public void getList() {
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			con = dbConnectt.getConnection();
			String sql = "SELECT * FROM EMPLOYEES";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.print(rs.getString(4)+"\t");
				System.out.print(rs.getString(5)+"\t");
				System.out.print(rs.getString(6)+"\t");
				System.out.print(rs.getString(7)+"\t");
				System.out.print(rs.getString(8)+"\t");
				System.out.print(rs.getString(9)+"\t");
				System.out.print(rs.getString(10)+"\t");
				System.out.println(rs.getString(11));
				System.out.println("========================================================================================================================");

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} finally {
			
			dbConnectt.disConnection(rs, st, con);
			
		}
		
		
	}
	
	
	public void getOne(int employee_id) {
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			con = dbConnectt.getConnection();
			
			String sql = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";
			st = con.prepareStatement(sql);
			
			st.setInt(1, employee_id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.print(rs.getString(4)+"\t");
				System.out.print(rs.getString(5)+"\t");
				System.out.print(rs.getString(6)+"\t");
				System.out.print(rs.getString(7)+"\t");
				System.out.print(rs.getString(8)+"\t");
				System.out.print(rs.getString(9)+"\t");
				System.out.print(rs.getString(10)+"\t");
				System.out.println(rs.getString(11));
				
			} else {
				System.out.println("해당 번호 없음");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			
			dbConnectt.disConnection(rs, st, con);
			
		}
		
		
	}
	
	
	public void getSal(int min, int max) {
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
						
			con = dbConnectt.getConnection();
			String sql = "SELECT EMPLOYEE_ID, SALARY FROM EMPLOYEES WHERE SALARY BETWEEN ? AND ?";
			st = con.prepareStatement(sql);
			st.setInt(1, min);
			st.setInt(2, max);
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				System.out.print(rs.getInt(1)+"\t");
				System.out.println(rs.getInt(2));				
				System.out.println("--------------------------------------------");
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			
			dbConnectt.disConnection(rs, st, con);
			
			
		}
		
		
	}
	
	
	public void search(String name) {
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			con = dbConnectt.getConnection();
			
			String sql = "SELECT LAST_NAME FROM EMPLOYEES WHERE LAST_NAME LIKE '%"+name+"%'";
			
			st = con.prepareStatement(sql);
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				System.out.println(rs.getString("LAST_NAME"));
				System.out.println("------------------------------");
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			
			dbConnectt.disConnection(rs, st, con);
			
			
		}
		
		
		
	}
	
	

}
