package com.revature.service;

import java.util.List;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.RequestDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Request;

public class ManagerService extends EmployeeService {
	private static ManagerService ms;
	
	private ManagerService() {
		super();
	}
	
	public static ManagerService getManagerServiceInstance() {
		if (ms == null)
			ms = new ManagerService();
		return ms;
	}
	public Manager getManagerRecord (String username) {
		return EmployeeDaoImpl.getEmployeeDaoImplInstance().getManager(username);
	}
	
	
	public boolean addNewEmployeeRecord(Employee e){
		return EmployeeDaoImpl.getEmployeeDaoImplInstance().addNewEmployee(e);
	}
	
	public List<Employee> getAllEmployeeRecord(){
		return EmployeeDaoImpl.getEmployeeDaoImplInstance().getAllEmployee();
	}
	
	public List<Request> getAllUnapprovedRequest(){
		return RequestDaoImpl.getRequestDaoInstance().getAllUnapprovedRequest();
	}
	
	public boolean updateApprovalStatus(long requestID, int managerID, String status) {
		return RequestDaoImpl.getRequestDaoInstance().updateApprovalStatus(requestID, managerID, status);
	}
}
