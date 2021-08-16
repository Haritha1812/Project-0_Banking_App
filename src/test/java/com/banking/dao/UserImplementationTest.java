package com.banking.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banking.exception.InsufficientBalanceException;
import com.banking.exception.NegativeAmountException;
import com.banking.model.User;

public class UserImplementationTest {
 UserDAO userDAO;
 User user = new User();
 Connection connection = com.banking.util.DBConnection.getDBConnection();
 PreparedStatement st = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userDAO = new UserImplementation();
	}

	@After
	public void tearDown() throws Exception {
		userDAO = null;
	}

	@Test
	public void testAddAccount() {
		User user1 = new User(-99, "demo","edemo","45342",100,"asdf");
		userDAO.addAccount(user1);
		userDAO.deleteAccount(-99);
		
	}

	@Test
	public void testDeleteAccount() {
		
	}

	

	@Test
	public void testWithdrawAmount() throws SQLException {
		int balance = 100;
		st = connection.prepareStatement("insert into hr.Users  values(-99,'demo','edemo',45342,100,'asdf')");
		st.executeUpdate();
		try {
			userDAO.withdrawAmount(-99, 50);
		} catch (InsufficientBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int newbalance = userDAO.viewBalance(-99);
		assertEquals(balance, newbalance+50);
		userDAO.deleteAccount(-99);
		
	}
	     
	@Test
	public void testViewBalance() {
		User user1 = new User(1, "demo","edemo","45342",100,"asdf");
		userDAO.addAccount(user1);
		User user2 = new User(2, "demo","edemo","45342",100,"asdf");
		userDAO.addAccount(user2);
		assertEquals(userDAO.viewBalance(1),userDAO.viewBalance(2));
		userDAO.deleteAccount(1);
		userDAO.deleteAccount(2);
	}

	@Test
	public void testDepositAmount() throws NegativeAmountException {
		int balance = 100;
		User user1 = new User(-99, "demo","edemo","45342",100,"asdf");
		userDAO.addAccount(user1);
		try {
			userDAO.depositAmount(-99, 50);
		} catch (NegativeAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int newbalance = userDAO.viewBalance(-99);
		assertEquals(balance +50 , newbalance);
	   userDAO.deleteAccount(-99);
	
		
	}

	

	@Test
	public void testTransferAmount() {
		User user1 = new User(1, "demo","edemo","45342",500,"asdf");
		userDAO.addAccount(user1);
		User user2 = new User(2, "demo","edemo","45342",0,"asdf");
		userDAO.addAccount(user2);
		try {
			userDAO.transferAmount(1, 2, 100);
		} catch (InsufficientBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(userDAO.viewBalance(1));
		System.out.println(userDAO.viewBalance(2));
		assertEquals(userDAO.viewBalance(1),userDAO.viewBalance(2)+300);
		userDAO.deleteAccount(1);
		userDAO.deleteAccount(2);
		
	}

}
