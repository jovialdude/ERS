package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.util.JDBCConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	private static EmployeeDaoImpl empDao = null;
	final static Logger log = Logger.getLogger(EmployeeDaoImpl.class);
	private static JDBCConnectionUtil cu = JDBCConnectionUtil.getInstance();

	private EmployeeDaoImpl() {
	}

	public static EmployeeDaoImpl getEmployeeDaoImplInstance() {
		if (empDao == null)
			empDao = new EmployeeDaoImpl();
		return empDao;
	}

	@Override
	public boolean addNewEmployee(Employee emp) {
		Connection conn = cu.getConnection();
		String sql = "CALL ADD_NEW_EMPLOYEE(?,?,?,?,?,?,?,?,?)";

		try {
			CallableStatement cs = conn.prepareCall(sql);

			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, emp.getFirstname());
			cs.setString(3, emp.getLastname());
			cs.setString(4, emp.getUsername());
			cs.setString(5, emp.getPassword());
			cs.setString(6, emp.getEmail());
			cs.setString(7, emp.getAddress());
			cs.setString(8, emp.getTitle());
			cs.setString(9, emp.getIsEmployed());

			if (cs.executeUpdate() > 0) {
				emp.setID(cs.getInt(1));
				return true;
			}
			log.info(emp.toString() + " is added to the database");
		} catch (SQLException e) {
			log.error(e, e);
		}

		return false;
	}

	@Override
	public Employee getEmployee(String userName) {
		// TODO Auto-generated method stub
		Employee e = null;

		Connection conn = cu.getConnection();

		try {
			String sql = "SELECT * FROM employee_info WHERE e_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
					e = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			}
		} catch (SQLException sqle) {
			log.error(e);
		}
		return e;
	}
	
	@Override
	public Manager getManager(String userName) {
		// TODO Auto-generated method stub
		Manager e = null;

		Connection conn = cu.getConnection();

		try {
			String sql = "SELECT * FROM employee_info WHERE e_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
					e = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			}
		} catch (SQLException sqle) {
			log.error(e);
		}
		return e;
	}

	@Override
	public Boolean loginAuthorize(String username, String password) {
		Boolean isAuthorized = null;

		Connection conn = cu.getConnection();
		try {
			String sql = "SELECT COUNT(*) AS total FROM employee_info WHERE e_username = ? AND e_password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			rs.next();
			int temp = rs.getInt("total");
			System.out.println(temp);

			if (temp == 1) {
				isAuthorized = new Boolean("true");
				log.info(username + " logged in");
			}
			else {
				isAuthorized = new Boolean("false");
				log.info(username + " failed to login");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			log.error(sqle);
		}

		return isAuthorized;
	}

	@Override
	public String getRole(String username) {
		// TODO Auto-generated method stub
		String role = null;
		Connection conn = cu.getConnection();
		try {
			String sql = "SELECT E_JOBTITLE AS title FROM employee_info WHERE e_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			role = rs.getString("title");
		} catch (SQLException sqle) {
			log.error(sqle);
		}

		return role;
	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		List<Employee> allEmployee = null;
		Connection conn = cu.getConnection();

		try {
			String sql = "SELECT * FROM employee_info";
			PreparedStatement ps = conn.prepareStatement(sql);

			allEmployee = new ArrayList<Employee>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				allEmployee.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
			}
			log.info("Retrieving all employee info");
		} catch (SQLException e) {
			log.error(e);
		}

		return allEmployee;
	}

}
