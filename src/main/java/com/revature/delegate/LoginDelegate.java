package com.revature.delegate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.service.EmployeeService;
import com.revature.service.LoginService;
import com.revature.service.ManagerService;

public class LoginDelegate {

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String username = req.getParameter("user");
		String password = req.getParameter("pass");

		if (username == null || password == null)
			resp.sendRedirect("html/");
		if (LoginService.getLoginServiceInstance().loginAuthorization(username, password)) {
			switch (EmployeeService.getEmployeeServiceInstance().getRole(username).toLowerCase()) {
			case "employee":
//				System.out.println("in employee");
				Employee e = EmployeeService.getEmployeeServiceInstance().getEmployeeRecord(username);
				req.getSession(true).setAttribute("user", e);
				resp.sendRedirect("home/");
				break;
			case "manager":
				Manager m = ManagerService.getManagerServiceInstance().getManagerRecord(username);
				req.getSession(true).setAttribute("user", m);
				resp.sendRedirect("home/");
				break;
			default:
				resp.sendRedirect("html/");
				break;
			}
		} else {
			resp.sendRedirect("html/");
		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("invalidating session");
		if (req.getSession(false) != null)
			req.getSession().invalidate();
		// resp.setStatus(200);
		resp.sendRedirect("../html/");
		return;
	}
}
