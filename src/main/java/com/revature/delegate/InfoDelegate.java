package com.revature.delegate;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Request;
import com.revature.service.EmployeeService;
import com.revature.service.ManagerService;

public class InfoDelegate {
	public void getInfo(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession hs = req.getSession(false);

		Employee e = null;

		if (hs.getAttribute("user") instanceof Manager) {
			e = (Manager) hs.getAttribute("user");
		} else {
			e = (Employee) hs.getAttribute("user");
		}

		ObjectMapper mapper = new ObjectMapper();
		res.setHeader("Content-type", "application/json");
		mapper.writeValue(res.getOutputStream(), e);
		return;
	}

	public void getAllUnapprovedRequest(HttpServletRequest req, HttpServletResponse resp)
			throws JsonGenerationException, JsonMappingException, IOException {
		List<Request> requests = ManagerService.getManagerServiceInstance().getAllUnapprovedRequest();

		ObjectMapper mapper = new ObjectMapper();
		resp.setHeader("Content-type", "application/json");
		mapper.writeValue(resp.getOutputStream(), requests);
		return;
	}

	public void getAllRequestForSingleUser(HttpServletRequest req, HttpServletResponse resp)
			throws JsonGenerationException, JsonMappingException, IOException {
		int id = ((Employee) req.getSession().getAttribute("user")).getID();
		List<Request> requests = EmployeeService.getEmployeeServiceInstance().getAllRequestForAnEmployee(id);

		ObjectMapper mapper = new ObjectMapper();
		resp.setHeader("content-type", "application/json");
		mapper.writeValue(resp.getOutputStream(), requests);
	}

	public void addNewRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String reason = req.getParameter("reason");
		int amount = Integer.parseInt(req.getParameter("amount"));
		int id = ((Employee) req.getSession(false).getAttribute("user")).getID();
		Request r = new Request(id, reason, amount);

		if (EmployeeService.getEmployeeServiceInstance().createNewRequest(r) == true) {
			resp.getWriter().write("Submission successful!");
		} else {
			resp.getWriter().write("Submission failed. Please resubmit");
		}
	}

	public void updateRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int managerID = ((Manager) req.getSession(false).getAttribute("user")).getID();
		long requestID = Long.parseLong(req.getParameter("id"));
		String status = req.getParameter("status").toLowerCase();
		if (status.equals("approve"))
			status = "approved";
		else
			status = "denied";
		ManagerService.getManagerServiceInstance().updateApprovalStatus(requestID, managerID, status);
		resp.getWriter().write("Transaction is " + status);

	}
	
	public void getAllUserInfo(HttpServletRequest req, HttpServletResponse resp) throws JsonGenerationException, JsonMappingException, IOException {
		List<Employee> emp = ManagerService.getManagerServiceInstance().getAllEmployeeRecord();
		
		ObjectMapper mapper = new ObjectMapper();
		resp.setHeader("content-type", "application/json");
		mapper.writeValue(resp.getOutputStream(), emp);
	}
}

