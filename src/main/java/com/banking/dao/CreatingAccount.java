package com.banking.dao;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.banking.exception.NegativeAmountException;
import com.banking.model.Employee;
import com.banking.model.User;

public class CreatingAccount {
	Scanner sc =  new Scanner(System.in);
    boolean result;
    User user = new User();
    UserDAO userDAO = new UserImplementation();
    EmployeeDAO employeeDAO = new EmployeeDAOImplementation();
    Employee employee = new Employee();
    private static Logger logger = Logger.getLogger("BankApp");
      

public void createAccount() {
	
	   System.out.println("\n#######Welcome to Create Account Section #######");
       System.out.println("Enter your account type (1 for Employee /2 for User  ) : ");
   	   int accountType = sc.nextInt();
  	   if(accountType == 1)
  	   {
  		   
                 System.out.println("--------------Employee Account--------------- ");
                 System.out.println("*Fill the following details to create the account*");
                 employee = employeeaccountDetails();
                 result = employeeDAO.addEmployeeAccount(employee);
                 if (result)
                  	System.out.println(
                 	"#Employeeaccount with emloyeeId : " + employee.getEmployeeId() + " ,saved successfully");
                 else
                 System.out.println(
  	                 "#Employeeaccount with emloyeeId : " + employee.getEmployeeId()+ " ,not saved successfully");
                         System.out.println("Check whether all the details are entered correctly");
  	}
        
  	  else {
  		         System.out.println("User Account ");
     	         System.out.println("------Fill the following details to create the accont--------");
     	         user = acceptaccountDetails();
     	         result = userDAO.addAccount(user);
       
               	if (result) {    	
          	        System.out.println("....To activate the account you should deposit the minimum amount of Rs.500... ");
          	        System.out.println("\nPlease deposit Rs500 into your account");
          	        System.out.println("\nEnter the amount to deposit");
          	        int deposit = sc.nextInt();
          	        try {
						userDAO.depositAmount(user.getAccountNumber(), deposit);
						System.out.println("Waiting for approval......");
						System.out.println("\nYou request for account status is accepted .....");
						logger.info("Your account with account Number : " +user.getAccountNumber()+" is created successfully.");
						System.out.println("\n Please, note Your account number for future reference");
						System.out.println("\n Please login to your account to proceed further");
					} catch (NegativeAmountException e) {
						System.out.println("You should deposit atleast Rs 500 into your account...\n Your account request is denied by Bank employee");					}
          	                }
               	
          	   else
          	    {
	             	System.out.println(
				"#Useraccount with account number: " + user.getAccountNumber()+ ",not saved successfully");
	             	 System.out.println("Check whether all the details are entered correctly");
          	     }
  	        }
}


public Employee employeeaccountDetails() {
	        
	
	       System.out.println("Please enter UserName: ");
	       String employeeName = sc.next();
	       System.out.println("Please enter password to create: ");
	       String employeePassword = sc.next();
	       sc.nextLine();
	       System.out.println("Please enter EmpId");
	       int employeeId =sc.nextInt();
	       System.out.println("Please enter your address : ");
	       String employeeAddress = sc.next();
	       System.out.println("Please enter your phone number: ");
	       String employeephonenumber = sc.next();
	
	
	       Employee employee = new Employee(employeeId, employeeName, employeeAddress, employeephonenumber, employeePassword);
			
           return employee;
}

//function to 
public User acceptaccountDetails() {
	
	
	       Random generator = new Random();
           int accountNumber = generator.nextInt(89999);
           accountNumber = accountNumber + 10000;
           System.out.println("Please enter UserName: ");
           String userName = sc.next(); 
	       System.out.println("Please enter your address : ");
	       String address = sc.next();
	       System.out.println("Please enter your phone number: ");
	       String phonenumber = sc.next();
	       int balance =0;
	       System.out.println("Please enter password to create: ");
	       String userPassword = sc.next();
	       sc.nextLine();
	       //printing random account number
	       System.out.println("Your account number is"+accountNumber);

	       User user1 = new User(accountNumber, userName, address, phonenumber, balance, userPassword);
           System.out.println(user1);
           return user1;
}



}
