package com.banking.dao;
import java.awt.List;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.BankApp;
import com.banking.model.Employee;
import com.banking.model.User;
public class EmployeeLogin {
boolean result;
    
	  EmployeeDAO employeeDAO = new EmployeeDAOImplementation();
	  UserDAO userDAO = new UserImplementation();
      Employee employee = new Employee();
      Scanner sc =  new Scanner(System.in);
      
	public void loginAccount() {
		System.out.println("Enter your Employee id");
		int acc=sc.nextInt();
		System.out.println("Enter your password");
		String pass = sc.next();
		Employee emp = new Employee();
		emp = employeeDAO.validateAccount(acc, pass);
		  if(emp == null) {
			System.out.println("Invalid Login: Wrong account number or password");
		                  }
		  else if(emp != null){
			  int option =0;
			System.out.println("Login succesfull");

			System.out.println("Welcome "+ emp.getEmployeeName());
			
		     while(true) {
			
			System.out.println("------------");		
			System.out.println("1.View Account");
			System.out.println("2.Edit account details");
			System.out.println("3.Delete account");
			System.out.println("4.View details of User");
			System.out.println("5.Log of all transactions");
			System.out.println("6.Log of all CustomerTransaction by accountnumber");
			System.out.println("9.LogOut");
		
			System.out.println("Choose the option");
			option = sc.nextInt();
			switch(option) {
			case 1:
				//to view the account of user
			       employee = employeeDAO.viewEmployeeAccount(acc);
			       System.out.println("Welcome,"+ employee.getEmployeeName() + "\ns  You can see your account details here");
			       break;
			   
			case 2:
				//to update the account
				  employee = employeeaccountDetails();
			      result =employeeDAO.updateEmployeeAccount(employee);
		          if(result) {
				     System.out.println("Your account updated successfully");
			                  }
		          break;
			       
			case 3:
			
				//to delete the account
				 employeeDAO.deleteEmployeeAccount(acc);
				 break;
				
			case 4:
				//to get the details of all users
				 employeeDAO.getAllUserDetails();
				
				break;
				
				//to see the transaction details of all users.
			case 5:
				
				employeeDAO.viewTransactionDetails();
				break;
				
				//To see transaction details of the customer
			case 6:
				
				System.out.println("Enter the customer account number to see transaction");
				int accno = sc.nextInt();				
				boolean res = userDAO.isUserExists(accno);
				if(res) {
				System.out.println("The statement details of the user accountnumber : " +accno );
				
				employeeDAO.viewStatementOfUserById(accno);
				}
				else {
					System.out.println("No account with this accountnumber");
				}
				//to log out of the account
			case 9:
				
				BankApp bankapp = new BankApp();
				bankapp.startUserApp();
				System.out.println("You have logged out successfully");
					
			}
			
		     }
		
	}
	}

	/// method to update details of employee
 public Employee employeeaccountDetails() {
	 
	 try {
	System.out.println("Please enter EmpId");
	int employeeId =sc.nextInt();
	System.out.println("Please enter your address : ");
	String employeeAddress = sc.next();
	System.out.println("Please enter your phone number: ");
	String employeephonenumber = sc.next();
	System.out.println("Please enter password to create: ");
	String employeePassword = sc.next();
	sc.nextLine();
	System.out.println("Please enter UserName: ");
	String employeeName = sc.next();
	
	Employee employee = new Employee(employeeId, employeeName, employeeAddress, employeephonenumber, employeePassword);
			
    
}
	 catch(InputMismatchException e) {
		 System.out.println("Check the Inputs");
	 }
 	
     return employee;
	}
}
