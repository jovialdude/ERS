package com.revature.model;

public class Request {
	private long ID;
	private String reason;
	private String approvalStatus;
	private Integer	ManagerID;
	private int EmployeeID;
	public int requestAmount;
	
	
	//
	// For creation of a new user to input to the database
	//
	public Request(int employeeID, String reason, int amount) {
		super();
		this.ManagerID = null;
		this.EmployeeID = employeeID;
		this.reason = reason;
		this.approvalStatus = "pending";
		this.requestAmount = amount;
	}

	//
	// For object creation after data requested from database
	//
	public Request(long iD, Integer managerID, int employeeID, String reason, String approvalStatus, int amount) {
		super();
		this.ID = iD;
		this.approvalStatus = approvalStatus;
		this.ManagerID = managerID;
		this.reason = reason;
		this.EmployeeID = employeeID;
		this.requestAmount = amount;
	}


	public long getID() {
		return ID;
	}


	public void setID(long iD) {
		this.ID = iD;
	}

	
	public String getReason() {
		return this.reason;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}


	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}


	public Integer getManagerID() {
		return this.ManagerID;
	}


	public void setManagerID(Integer managerID) {
		this.ManagerID = managerID;
	}


	public int getEmployeeID() {
		return EmployeeID;
	}


	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}

	
	public int getRequestAmount() {
		return requestAmount;
	}

	
	public void setRequestAmount(int requestAmount) {
		this.requestAmount = requestAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + EmployeeID;
		result = prime * result + ManagerID;
		result = prime * result + ((approvalStatus == null) ? 0 : approvalStatus.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (EmployeeID != other.EmployeeID)
			return false;
		if (ID != other.ID)
			return false;
		if (ManagerID != other.ManagerID)
			return false;
		if (approvalStatus == null) {
			if (other.approvalStatus != null)
				return false;
		} else if (!approvalStatus.equals(other.approvalStatus))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Request [ID=" + ID + ", approvalStatus=" + approvalStatus + ", ManagerID="
				+ ManagerID + ", EmployeeID=" + EmployeeID + ", Reasoning="+reason+"]";
	}
	
	
}
