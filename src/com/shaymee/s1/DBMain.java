package com.shaymee.s1;

import com.shaymee.s1.location.LocationDAO;
import com.shaymee.s1.test.DBTest;
import com.shaymee.s1.test.DBTest_forLecture;

public class DBMain {

	public static void main(String[] args) {
		System.out.println("= DB Start =");
		//1. DB server에 접속
		//2. Login
		//3. SQL문(QUERY문) 작성해서 보내기
		//4. 결과
		
		DBTest_forLecture dbTest = new DBTest_forLecture();
//		dbTest.getList();
		
		LocationDAO locationDAO = new LocationDAO();
//		locationDAO.getList();
	
//		dbTest.getOne();
		
//		locationDAO.getOne(1300);
		
		dbTest.getCount();
		
		
		
		
		System.out.println("= DB fin. =");
	}

}
