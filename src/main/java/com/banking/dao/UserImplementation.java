package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;

import java.lang.Math;

import com.banking.exception.InsufficientBalanceException;
import com.banking.exception.NegativeAmountException;
import com.banking.model.Employee;
import com.banking.model.User;


public class UserImplementation implements UserDAO {
	Connection connection = com.banking.util.DBConnection.getDBConnection();
	Scanner sc = new Scanner(System.in);
	
	private static Logger logger = Logger.getLogger("UserImplementation");
	private final String ADD_ACCOUNT_QUERY = "insert into hr.Users values(?,?,?,?,?)";
    private final String VIEW_BALANCE_QUERY = "select * from hr.Users where accountNumber = ?";
    private final String WITHDRAW_AMOUNT_QUERY = "update hr.Users set balance = balance - ? where accountNumber = ?";
	private final String DELETE_ACCOUNT_QUERY = "delete from hr.Users where accountNumber = ?";
	private final String UPDATE_ACCOUNT_QUERY = "update hr.Users set userName=? , address  = ? ,phoneNumber =?,balance = ? ,userPassword = ? where accountNumber = ?";
	private final String TRANSFER_AMOUNT_QUERY = "call hr.transferAmount(?,?,?)";
	public boolean addAccount(User user1) {
	
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement("insert into hr.Users values(?,?,?,?,?,?)");
			statement.setInt(1, user1.getAccountNumber());
			statement.setString(2, user1.getUserName());
			statement.setString(3, user1.getAddress());
			statement.setString(4, user1.getPhoneNumber());
			statement.setInt(5, user1.getBalance());
			statement.setString(6, user1.getUserPassword());

			res = statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;
      	}
		
		

	public boolean deleteAccount(int accountNumber) {
		
		int res =0;
		
		PreparedStatement st;
		try {
			st = connection.prepareStatement(DELETE_ACCOUNT_QUERY);
			st.setInt(1,accountNumber);
			res = st.executeUpdate();
			logger.info("Your account with account Number : " +accountNumber+" is successfully deleted.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(res ==0)
		return false;
		else
			return true;
		
		
	}

	public boolean updateAccount(User user) {
		
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT_QUERY);
			
			statement.setInt(6, user.getAccountNumber());
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getAddress());
			statement.setString(3, user.getPhoneNumber());
			statement.setInt(4, user.getBalance());
			statement.setString(5, user.getUserPassword());

			res = statement.executeUpdate();}
	    catch (SQLException e) {
	  		e.printStackTrace();
	        	}

		if (res == 0)
			return false;
		else
			return true;
		
	          }



	public User validateAccount(int accountNumber, String userPassword) {
		User user =null;
				try {
					PreparedStatement statement=connection.prepareStatement("select * from hr.Users where accountNumber=? and userPassword=?");
					statement.setInt(1, accountNumber);
					statement.setString(2,userPassword);
					ResultSet resultSet=statement.executeQuery();
					while(resultSet.next())
					{
						user=new User();
						user.setAccountNumber(resultSet.getInt(1));
						user.setUserName(resultSet.getString(2));
						user.setUserPassword(resultSet.getString(6));
						user.setPhoneNumber(resultSet.getString(4));
						user.setAddress(resultSet.getString(3));
									user.setBalance(resultSet.getInt(5));	
					}
				} 
				catch (SQLException e) {
					logger.info("LOGIN ERRORR!!.Please check the accountNumber and password");
				}
				
				return user;
				
	}
	public User viewUserAccount(int accountNumber) {
		User userdata = null;
		try {
			PreparedStatement statement=connection.prepareStatement("select * from hr.Users where  accountNumber=? ");
			statement.setInt(1, accountNumber);
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				userdata = new User();
				userdata.setAccountNumber(resultSet.getInt(1));
				userdata.setUserName(resultSet.getString(2));
				userdata.setAddress(resultSet.getString(3));
				userdata.setPhoneNumber(resultSet.getString(4));
				userdata.setBalance(resultSet.getInt(5));
				userdata.setUserPassword(resultSet.getString(6));
							
			}
		} 
		catch (SQLException e) {
		logger.info("Server Problem......Might catch you soon");
		}
		
		return userdata;
		
		
	}



	public int withdrawAmount(int accountNumber , int amount) throws InsufficientBalanceException {
		int balance = viewBalance(accountNumber);
	    // System.out.println(balance);
		if(amount < balance)
		
		try {
			PreparedStatement statement=connection.prepareStatement("update hr.Users set balance = balance - ? where accountNumber = ?");
			statement.setInt(1, amount);
			statement.setInt(2,accountNumber);
			statement.executeUpdate();
			balance = viewBalance(accountNumber);
			
			logger.info("Your have withdrawn  Rs: " + amount+ " successfully.");
			}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		else 
			 throw new InsufficientBalanceException("You have Insufficient Balance");
		
		
		try {
			User user = new User();
			PreparedStatement statement = connection.prepareStatement("insert into hr.customerTransaction values(?,?,?,?,?)");
			
			statement.setInt(1,accountNumber);
			//statement.setDate(2, (java.sql.Date) date);
			statement.setInt(2, 0);
			statement.setInt(3, amount);
			statement.setInt(4, 0);
			statement.setInt(5,balance);
		 statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		
		
		return 0;
		
	}



	public int viewBalance(int accountNumber) {
		User userdata = null;
		int accountBalance = 0;
		try {
			PreparedStatement statement=connection.prepareStatement("select * from hr.Users where  accountNumber=? ");
			statement.setInt(1, accountNumber);
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				userdata = new User();
				userdata.setAccountNumber(resultSet.getInt(1));
				userdata.setUserName(resultSet.getString(2));
				userdata.setAddress(resultSet.getString(3));
				userdata.setPhoneNumber(resultSet.getString(4));
				userdata.setBalance(resultSet.getInt(5));
				userdata.setUserPassword(resultSet.getString(6));
						 accountBalance = userdata.getBalance();
			}
		} 
		catch (SQLException e) {
			
		}
		
		return accountBalance;
		
		
	}



	public int depositAmount(int accountNumber, int cash) throws NegativeAmountException {
		int balance = viewBalance(accountNumber);
	    // System.out.println(balance);
		if(cash > 0)
		
			try {
			PreparedStatement statement=connection.prepareStatement("update hr.Users set balance = balance + ? where accountNumber = ?");
			statement.setInt(1, cash);
			statement.setInt(2,accountNumber);
			statement.executeUpdate();
			balance = viewBalance(accountNumber);
			
			logger.info("Your have deposited  Rs:" + cash+ " into your account number " + accountNumber+" successfully");
			}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		else 
			 throw new NegativeAmountException("You cannot deposit negative amount into your account");
		
		
		try {
			User user = new User();
			PreparedStatement statement = connection.prepareStatement("insert into hr.customerTransaction values(?,?,?,?,?)");
			
			statement.setInt(1,accountNumber);
			//statement.setDate(2, (java.sql.Date) date);
			statement.setInt(2, cash);
			statement.setInt(3, 0);
			statement.setInt(4, 0);
			statement.setInt(5,balance );
		 statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		return 0;
	}



	public void transferAmount(int senderAccountNumber, int reciverAccountNumber , int amount)throws InsufficientBalanceException {
		int balance = viewBalance(senderAccountNumber);
		Date date=java.util.Calendar.getInstance().getTime();
		if(amount < balance) {
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(TRANSFER_AMOUNT_QUERY);
			statement.setInt(1, senderAccountNumber);
			statement.setInt(2,reciverAccountNumber);
			statement.setInt(3, amount);
			statement.executeUpdate();
			logger.info("Transfering Rs:" + amount + "to account Number :"+reciverAccountNumber );
			System.out.println("Transaction Successfull");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}
		else {
			throw new InsufficientBalanceException("You have Insufficient Balance to transfer");
	

		}
		try {
			User user = new User();
			PreparedStatement statement = connection.prepareStatement("insert into hr.customerTransaction values(?,?,?,?,?,?)");
			
			statement.setInt(1,senderAccountNumber);
		//	statement.setDate(2, (java.sql.Date) date);
			statement.setInt(2, 0);
			statement.setInt(3, 0);
			statement.setInt(4, amount);
			statement.setInt(5,viewBalance(senderAccountNumber));
			statement.setInt(6, reciverAccountNumber);
		 statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}



	public boolean isUserExists(int accountNUmber) {
		
		boolean result=false;
		try {
			PreparedStatement statement=connection.prepareStatement("select * from hr.Users where accountNumber = ?");
			statement.setInt(1, accountNUmber);
			ResultSet resultSet=statement.executeQuery();
			if(resultSet.next())
				result=true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

		
	}	
	
}
