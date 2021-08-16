package com.banking.model;

import java.io.Serializable;

public class Employee implements Serializable {
	
		private int employeeId;
		private String employeeName;
		private String employeeAddress;
		private String employeePhoneNumber;
		private String employeePassword;
		
		
	public Employee(int employeeId, String employeeName, String employeeAddress, String employeePhoneNumber,
				String employeePassword) {
			super();
			this.employeeId = employeeId;
			this.employeeName = employeeName;
			this.employeeAddress = employeeAddress;
			this.employeePhoneNumber = employeePhoneNumber;
			this.employeePassword = employeePassword;
		}


	public Employee() {
		// TODO Auto-generated constructor stub
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getEmployeeAddress() {
		return employeeAddress;
	}


	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}


	public String getEmployeePhoneNumber() {
		return employeePhoneNumber;
	}


	public void setEmployeePhoneNumber(String employeePhoneNumber) {
		this.employeePhoneNumber = employeePhoneNumber;
	}


	public String getEmployeePassword() {
		return employeePassword;
	}


	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}


	

	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeAddress="
				+ employeeAddress + ", employeePhoneNumber=" + employeePhoneNumber + ", employeePassword="
				+ employeePassword + "]";
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
