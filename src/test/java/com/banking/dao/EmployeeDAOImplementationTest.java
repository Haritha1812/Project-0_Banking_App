package com.banking.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banking.model.Employee;
import com.banking.model.User;

public class EmployeeDAOImplementationTest {
      EmployeeDAO employeeDAO;
      PreparedStatement st = null;
      Connection connection = com.banking.util.DBConnection.getDBConnection();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		employeeDAO = new EmployeeDAOImplementation();
	}

	@After
	public void tearDown() throws Exception {
		employeeDAO =null;
	}

	@Test
	public void testAddEmployeeAccount() {
		int accountNumber = -1;
		
		List<User> userdetails1 = employeeDAO.getAllUserDetailsByList();
	    UserDAO userDAO = new UserImplementation();
	    
	    userDAO.addAccount(new User(accountNumber, "demo","edemo","45342",100,"asdf"));
	    List<User> userdetails2 = employeeDAO.getAllUserDetailsByList();
	   assertEquals(userdetails1.size()+1, userdetails2.size());
	    userDAO.deleteAccount(-1);
	}

	@Test
	public void testDeleteEmployeeAccount() {
int accountNumber = -1;
		
		List<User> userdetails1 = employeeDAO.getAllUserDetailsByList();
	    UserDAO userDAO = new UserImplementation();
	    
	    userDAO.addAccount(new User(accountNumber, "demo","edemo","45342",100,"asdf"));
	    List<User> userdetails2 = employeeDAO.getAllUserDetailsByList();
	    userDAO.deleteAccount(-1);
	    assertEquals(userdetails1.size(), userdetails2.size()-1);
	    
	}
	

	@Test
	public void testViewEmployeeAccount() {
int accountNumber = -1;
		
		List<User> userdetails1 = employeeDAO.getAllUserDetailsByList();
	    UserDAO userDAO = new UserImplementation();
	    userDAO.addAccount(new User(accountNumber, "demo","edemo","45342",100,"asdf"));
	    List<User> userdetails2 = employeeDAO.getAllUserDetailsByList();
	   assertEquals(userdetails1.size()+1, userdetails2.size());
	    userDAO.deleteAccount(-1);
	}
	
	

}
