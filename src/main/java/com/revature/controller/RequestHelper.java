package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegate.HomeDelegate;
import com.revature.delegate.InfoDelegate;
import com.revature.delegate.LoginDelegate;

public class RequestHelper {
	private HomeDelegate hd = new HomeDelegate();
	private LoginDelegate ld = new LoginDelegate();
	private InfoDelegate id = new InfoDelegate();

	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String switchString = req.getRequestURI().substring(req.getContextPath().length() + 1);

		while (switchString.indexOf("/") > 0) {
			switchString = switchString.substring(0, switchString.indexOf("/"));
		}
		switch (switchString) {
		case "home":
			hd.goHome(req, resp);
			break;
		case "login":
			if ("POST".equals(req.getMethod())) {
				ld.login(req, resp);
			}
			break;
		case "info":
			id.getInfo(req, resp);
			break;
		case "manager":
			id.getAllUnapprovedRequest(req, resp);
			break;
		case "allusers":
			id.getAllUserInfo(req, resp);
			break;
		case "update":
			id.updateRequest(req, resp);
			break;
		case "user":
			id.getAllRequestForSingleUser(req, resp);
			break;
		case "request":
			id.addNewRequest(req, resp);
			break;
		case "logout":
			ld.logout(req, resp);
			break;
		default:
			resp.sendRedirect("../html/404.html");
			break;
		}
		return;
	}	
}
