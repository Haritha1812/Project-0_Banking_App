package com.banking.model;

import java.io.Serializable;

public class User implements Serializable {
	
	
	private int accountNumber;
	private String userName;
	private String address;
	private String phoneNumber;
	private int balance =500;
	private String userPassword;
// getters and seters
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	
 //// getting constructors
	
	@Override
	public String toString() {
		return "\n User [accountNumber=" + accountNumber + ", userName=" + userName + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", balance=" + balance + ", userPassword=" + userPassword + "]";
	}
	public User(int accountNumber, String userName, String address, String phonenumber, int balance, String userPassword) {
		super();
		this.accountNumber = accountNumber;
		this.userName = userName;
		this.address = address;
		this.phoneNumber = phonenumber;
		this.balance = balance;
		this.userPassword = userPassword;
	}
	
	//////creating hashcode and equals
	
	public User() {
		// TODO Auto-generated constructor stub
	}
}