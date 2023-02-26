package com.masai.dto;

public class EmployeeImpl implements Employee {

	private int empID;
	private String empName;
	private String empUserName;
	private String empPassword;

	public EmployeeImpl() {
	}

	public EmployeeImpl( String empName, String empUserName, String empPassword) {
		this.empName = empName;
		this.empUserName = empUserName;
		this.empPassword = empPassword;
	}
	public EmployeeImpl(int empID, String empName, String empUserName, String empPassword) {
		super();
		this.empID = empID;
		this.empName = empName;
		this.empUserName = empUserName;
		this.empPassword = empPassword;
	}

	@Override
	public int getEmpID() {
		return empID;
	}

	@Override
	public void setEmpID(int empID) {
		this.empID = empID;
	}

	@Override
	public String getEmpName() {
		return empName;
	}

	@Override
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Override
	public String getEmpUserName() {
		return empUserName;
	}

	@Override
	public void setEmpUserName(String empUserName) {
		this.empUserName = empUserName;
	}

	@Override
	public String getEmpPassword() {
		return empPassword;
	}

	@Override
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	@Override
	public String toString() {
		return "Employee [empID=" + empID + ", empName=" + empName + ", empUserName=" + empUserName + ", empPassword="
				+ empPassword + "]";
	}
}