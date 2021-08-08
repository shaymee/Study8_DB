package com.shaymee.s1;

import com.shaymee.s1.employee.EmployeeDAO;

public class DBMain2_Employee {

	public static void main(String[] args) {
		System.out.println("== EMPLOYEE MAIN START ==");

		EmployeeDAO employeeDAO = new EmployeeDAO();
		
//		employeeDAO.getList();
		
//		employeeDAO.getOne(105);
		
//		employeeDAO.getSal(5000, 20000);
		
		employeeDAO.search("b");
		
		
		System.out.println("== EMPLOYEE MAIN FINISH ==");
	}

}
