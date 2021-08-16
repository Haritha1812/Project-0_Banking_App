package com.banking.dao;
import java.util.Scanner;

import com.banking.model.Employee;
import com.banking.model.User;

public class Login {

	
	    boolean result;
	    EmployeeDAO employeeDAO = new EmployeeDAOImplementation();	
        Employee employee = new Employee();
        Scanner sc =  new Scanner(System.in);
        
        
    public void loginToTheAccount() {
    	 
		System.out.println("#######Welcome to Login Section #######");
		System.out.println("Enter your account type ( 1 for Employee / 2 for User ) : ");
		int accType = sc.nextInt();
		if(accType == 1) {
			EmployeeLogin emplogin = new EmployeeLogin();
			emplogin.loginAccount();
		}
		else {
			UserLogin userlogin = new UserLogin();
			userlogin.loginUserAccount();
		
		}
}
}