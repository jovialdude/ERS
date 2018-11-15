package com.revature.model;

public class Manager extends Employee {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3635699709697347197L;

	/**
	 * 
	 */
	
	public Manager(String firstname, String lastname, String username, String password, String email, String address,
			String isEmployed) {
		super(firstname, lastname, username, password, email, address, isEmployed);
		this.title="manager";
		// TODO Auto-generated constructor stub
	}

	public Manager(int ID, String firstname, String lastname, String username, String password, String email,
			String address, String title, String isEmployed) {
		super(ID, firstname, lastname, username, password, email, address, title, isEmployed);
		// TODO Auto-generated constructor stub
	}
	
}	
