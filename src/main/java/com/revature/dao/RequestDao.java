package com.revature.dao;

import java.util.List;

import com.revature.model.Request;

public interface RequestDao {
	public boolean addNewRequest(Request r);
	public Request getRequest(int id);
	public List<Request> getAllUnapprovedRequestForAnEmployee(int employee_id);
	public List<Request> getAllApprovedRequestForAnEmployee(int employee_id);
	public List<Request> getAllRequestForAnEmployee(int employee_id);
	public List<Request> getAllUnapprovedRequest();
	public boolean updateApprovalStatus(long transactionid, int managerid, String status);
}
