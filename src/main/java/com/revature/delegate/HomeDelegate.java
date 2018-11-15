package com.revature.delegate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Employee;
import com.revature.model.Manager;

public class HomeDelegate {
	public void goHome(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Get our session information
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(60*15);
		Employee emp = null;
		Manager manager = null;
		if (session.getAttribute("user") instanceof Manager) {
			manager = (Manager)session.getAttribute("user");
			String filePath = "/Users/DatMoneyNumeroDos/Documents/Revature/1810_oct8_jta/projects/Project1_ERS/src/main/webapp/html/manager/managerIndex.html";
			
			BufferedReader in = new BufferedReader(new FileReader (filePath));
			
			String temp = in.readLine();
			while (temp!= null) {
				resp.getWriter().write(temp + "\n");
				temp = in.readLine();
			}
			in.close();
			return;
		}
		else if (session.getAttribute("user") instanceof Employee) {
			emp = (Employee)session.getAttribute("user");
			///
			//here is the code for displaying the options on website
			//
			String filePath = "/Users/DatMoneyNumeroDos/Documents/Revature/1810_oct8_jta/projects/Project1_ERS/src/main/webapp/html/user/userIndex.html";
			
			BufferedReader in = new BufferedReader(new FileReader (filePath));
			String temp = in.readLine();
			while (temp!= null) {
				resp.getWriter().write(temp + "\n");
				temp = in.readLine();
			}
			in.close();
		}
		else {
			resp.sendRedirect("html/");
		}
		return;
	}
}
