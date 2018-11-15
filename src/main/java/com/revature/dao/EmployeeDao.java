package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Manager;

public interface EmployeeDao {
	public boolean addNewEmployee(Employee e);
	public Employee getEmployee(String username);
	public Manager getManager(String username);
	public Boolean loginAuthorize(String username, String password);
	public String getRole(String username);
	public List<Employee> getAllEmployee();
}
