package com.revature.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7538064724144803875L;
	RequestHelper rh = new RequestHelper();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getPathInfo() == null || req.getPathInfo().length() <= 2) {
			if (req.getSession(false) != null) {
				resp.sendRedirect("home/");
				return;
			}
			else {
				resp.sendRedirect(req.getContextPath() + "/html/");
			}
			return;
		}
		else if (req.getRequestURI().substring(req.getContextPath().length()).startsWith("/html/")) {
			if (req.getSession(false) != null) {
				resp.sendRedirect("home/");
				return;
			}
			
			resp.addHeader("Content-Type", "text/html");
			
			String fileName = req.getRequestURI().substring(req.getContextPath().length());
			String filePath = "/Users/DatMoneyNumeroDos/Documents/Revature/1810_oct8_jta/projects/Project1_ERS/src/main/webapp";
			
			while(fileName.indexOf("/")>0) {
				fileName = fileName.substring(fileName.indexOf("/")+1);
			}
			
					
			if (fileName.equals("/html/")||fileName.equals("/html"))
				fileName = "/html/index.html";

			BufferedReader in = new BufferedReader(new FileReader ((filePath+fileName)));
			
			String temp = in.readLine();
			while (temp!= null) {
				resp.getWriter().write(temp + "\n");
				temp = in.readLine();
			}
			in.close();
			return;
		}
		else if (req.getRequestURI().substring(req.getContextPath().length()).startsWith("/js/")) {
			resp.addHeader("Content-Type", "text/html");
			
			String fileName = req.getRequestURI().substring(req.getContextPath().length());
			String filePath = "/Users/DatMoneyNumeroDos/Documents/Revature/1810_oct8_jta/projects/Project1_ERS/src/main/webapp";
			
			while(fileName.indexOf("/")>0) {
				fileName = fileName.substring(fileName.indexOf("/")+1);
			}

			BufferedReader in = new BufferedReader(new FileReader ((filePath+fileName)));
			
			String temp = in.readLine();
			while (temp!= null) {
				resp.getWriter().write(temp + "\n");
				temp = in.readLine();
			}
			in.close();
		}
		else {
//			System.out.println("In Else");
			rh.process(req, resp);
		}
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		return;
	}

}
