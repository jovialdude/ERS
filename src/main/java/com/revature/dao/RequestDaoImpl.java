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

import com.revature.model.Request;
import com.revature.util.JDBCConnectionUtil;

public class RequestDaoImpl implements RequestDao {
	private static RequestDaoImpl reqDao = null;
	JDBCConnectionUtil cu = JDBCConnectionUtil.getInstance();
	private static Logger log = Logger.getLogger(RequestDaoImpl.class);
	
	private RequestDaoImpl() {}
	
	public static RequestDaoImpl getRequestDaoInstance() {
		if (reqDao == null)
			reqDao = new RequestDaoImpl();
		return reqDao;
	}
	
	@Override
	public boolean addNewRequest(Request r) {
		// TODO Auto-generated method stub
		Connection conn = cu.getConnection();
		String sql = "CALL ADD_NEW_TRANSACTION(?,?,?,?)";
		
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, Types.BIGINT);
			cs.setInt(2, r.getEmployeeID());
			cs.setString(3, r.getReason());
			cs.setInt(4, r.getRequestAmount());
			
			cs.executeUpdate();
			r.setID(cs.getLong(1));

			log.info(r.toString() + " added");

		} catch (SQLException e) {
			log.error(e);
			return false;
		}
		
		return true;
	}

	@Override
	public Request getRequest(int id) {
		// TODO Auto-generated method stub
		Connection conn = cu.getConnection();
		Request r = null;
		
		try {
			String sql = "SELECT * FROM request_info WHERE request_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				r = new Request(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6));
			}
			log.info("Request "+id+" accessed");
		} catch (SQLException sqle) {
			log.error(sqle);
		}
		return r;
	}

	@Override
	public List<Request> getAllRequestForAnEmployee(int employee_id) {
		// TODO Auto-generated method stub
		Connection conn = cu.getConnection();
		List<Request> r = null;
		
		try {
			String sql = "SELECT * FROM request_info WHERE employee_id =? ORDER BY request_status";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee_id);
			
			ResultSet rs = ps.executeQuery();
			System.out.println("after query execute");
			r = new ArrayList<Request>();
			System.out.println("after array list created");
			while (rs.next()) {
				r.add(new Request(rs.getLong(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
			}
			log.info("Requests for employee " + employee_id + " accessed");
		} catch (SQLException sqle) {
			log.error(sqle);
		}
		return r;
	}

	@Override
	public boolean updateApprovalStatus(long transactionid, int managerid, String status) {
		// TODO Auto-generated method stub
		Connection conn = cu.getConnection();
		try {
			String sql = "CALL update_request_status(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, status);
			cs.setInt(2, managerid);
			cs.setLong(3, transactionid);
			
			if (cs.executeUpdate() == 0) {
				log.info("Transaction "+transactionid+" is "+status+" by manager "+managerid);
				return true;
			}
			else
				log.info("Updating transaction "+transactionid+" failed");
		}catch (SQLException sqle) {
			log.error(sqle);
		}
		return false;
	}

	@Override
	public List<Request> getAllUnapprovedRequest() {
		Connection conn = cu.getConnection();
		List<Request> r = null;
		
		try {
			String sql = "SELECT * FROM request_info WHERE request_status='pending' ORDER BY request_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			r = new ArrayList<Request>();
			while (rs.next()) {
				r.add(new Request(rs.getLong(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
			}
			log.info("All unapproved reimbursement requested");
		} catch (SQLException sqle) {
			log.error(sqle);
		}
		return r;
	}

	@Override
	public List<Request> getAllUnapprovedRequestForAnEmployee(int employee_id) {
		Connection conn = cu.getConnection();
		List<Request> r = null;
		
		try {
			String sql = "SELECT * FROM request_info WHERE request_status='pending' AND e_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee_id);
			
			ResultSet rs = ps.executeQuery();
			r = new ArrayList<Request>();
			while (rs.next()) {
				r.add(new Request(rs.getLong(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
			}
			log.info("All unapproved reimbursement requested");
		} catch (SQLException sqle) {
			log.error(sqle);
		}
		return r;
	}

	@Override
	public List<Request> getAllApprovedRequestForAnEmployee(int employee_id) {
		Connection conn = cu.getConnection();
		List<Request> r = null;
		
		try {
			String sql = "SELECT * FROM request_info WHERE request_status='approved' AND e_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee_id);
			
			ResultSet rs = ps.executeQuery();
			r = new ArrayList<Request>();
			while (rs.next()) {
				r.add(new Request(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
			}
			log.info("All unapproved reimbursement requested");
		} catch (SQLException sqle) {
			log.error(sqle);
		}
		return r;
	}

}
