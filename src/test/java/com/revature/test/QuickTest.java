package com.revature.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.model.Employee;

public class QuickTest {
	private static final QuickTest quickTest = new QuickTest();
	
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testNewEmployeeCreation() {
		Employee e = new Employee("doctor", "who", "absoluteBrandNewUser", "123", "dwho@winning.com", "Gallifrey", "true");
		
		assertTrue(EmployeeDaoImpl.getEmployeeDaoImplInstance().addNewEmployee(e));
	}
}
