package com.revature.service;

import com.revature.dao.EmployeeDaoImpl;

public class LoginService {
	private static LoginService lg = null;
	
	private LoginService() {}
	
	public static LoginService getLoginServiceInstance() {
		if (lg == null)
			lg = new LoginService();
		return lg;
	}
	
	public Boolean loginAuthorization (String username, String password) {
		return EmployeeDaoImpl.getEmployeeDaoImplInstance().loginAuthorize(username, password);
	}
}
