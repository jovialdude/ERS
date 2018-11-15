package com.revature.service;


import java.util.List;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.RequestDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Request;

public class EmployeeService {
	
	private static EmployeeService empServ;

	protected EmployeeService() {
	}
	
	public static EmployeeService getEmployeeServiceInstance() {
		if (empServ == null)
			empServ = new EmployeeService();
		return empServ;
	}
	
	public Employee getEmployeeRecord (String username) {
		return EmployeeDaoImpl.getEmployeeDaoImplInstance().getEmployee(username);
	}
	
	public String getRole (String username) {
		return EmployeeDaoImpl.getEmployeeDaoImplInstance().getRole(username);
	}
	
	public boolean createNewRequest(Request r) {
		return RequestDaoImpl.getRequestDaoInstance().addNewRequest(r);
	}
//	
//	public List<Request> getAllUnapprovedRequestForAnEmployee(int id){
//		return RequestDaoImpl.getRequestDaoInstance().getAllUnapprovedRequestForAnEmployee(id);
//	}
	
	public List<Request> getAllRequestForAnEmployee(int id){
		return RequestDaoImpl.getRequestDaoInstance().getAllRequestForAnEmployee(id);
	}
}
