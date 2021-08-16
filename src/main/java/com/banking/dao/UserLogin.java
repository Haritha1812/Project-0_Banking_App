package com.banking.dao;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.BankApp;
import com.banking.exception.InsufficientBalanceException;
import com.banking.exception.NegativeAmountException;
import com.banking.model.Employee;
import com.banking.model.User;

public class UserLogin {
	//creating objects for classes
	 User user = new User();
	 UserDAO userDAO = new UserImplementation();	
	 boolean result;     
	 private static Logger logger = Logger.getLogger("BankApp");
	 Scanner sc =  new Scanner(System.in);
	
	 // method to login the account
	public void loginUserAccount() {
		User user = new User();		
		{
			System.out.println("Enter Your account number");
			int acc=sc.nextInt();
			System.out.println("Enter your password");
			String pass = sc.next();
			user = userDAO.validateAccount(acc, pass);
			if(user == null) {
				logger.info("Invalid Login: Wrong account number or password");
			}
			else if(user !=null) {
				System.out.println("Login succesfull");
				 logger.info("Welcome " +user.getUserName()+", You logged in Successfully");
					System.out.println("Welcome "+ user.getUserName());
		while(true) {
			   
				System.out.println("------------");		
				System.out.println("1.View Account");
			    System.out.println("2.View Balance");
				System.out.println("3.Update Account");
				System.out.println("4.Withdraw");
				System.out.println("5.Deposit");
				System.out.println("6.Transfer money");
				System.out.println("9.Exit..");
				System.out.println("Choose the option");
				
				int option = sc.nextInt();
				switch(option){
				//to view the account
				case 1:
					
					     logger.info("You can view your account here");
			             user= userDAO.viewUserAccount(acc);
				         System.out.println(user);
					     break;
				
			    //to view balance of the user
			    case 2:
			    	     int balance;
			             balance = userDAO.viewBalance(acc);
			             logger.info("Your Account Balance is  Rs:" + balance);
			    	     break;
			    //to update the details of the user    
			    case 3:
			    	
			             user = acceptaccountDetails(acc);
					     result =userDAO.updateAccount(user);
					     logger.info("Your account with accountnumber: " +acc+ "is updated successfully");
						 break;
						 
				//withdraw amount		 
                case 4:
                	
            	         System.out.println("Enter the amount to withdraw");   
					     int amount = sc.nextInt();
					     try {
						     userDAO.withdrawAmount(acc, amount);
					         } 
					     catch (InsufficientBalanceException e) {
						       logger.warn("Your balance is Insufficient.");
						       e.printStackTrace();
						       e.getMessage();
					         }
                   
                         break;
               //deposit the amount
                       
			   case 5:
				   
			        	 System.out.println("Enter the amount to deposit");   
						 int cash = sc.nextInt();
						 try {
								userDAO.depositAmount(acc, cash);
							}
						 catch (NegativeAmountException e) {
								logger.warn("You should deposit valid amount");
								e.printStackTrace();
								e.getMessage();
							}
                   
                       break;
              //transfer the amount         
              case 6:
            	  
                        System.out.println("Enter the accountnumber to transfer");
                        int accno = sc.nextInt();
                        System.out.println("Enter the amount to transfer");
                        int amt = sc.nextInt();
                        boolean res;
                        res = userDAO.isUserExists(accno);
                        if(res) {
					      try {
						        userDAO.transferAmount(acc, accno, amt);
					           } 
					      catch (InsufficientBalanceException e) {
					        	logger.info("Your balance is insufficient.You cannot initiate transaction");
					        	e.printStackTrace();
						        e.getMessage(); 
					            }}
                        else {
                        		System.out.println("Incorrect account Number.Please check it");
                        	}
                        	
                        break;
              //to delete the account     
             case 7:
                        userDAO.deleteAccount(acc);
						break;
                       
			 case 9:
			        	BankApp bankapp = new BankApp();
			        	bankapp.startUserApp();
			        	System.out.println("Logging out......");
			        	logger.info("You logged out succesfully");		        		
			            break;
			        
					
			}
					
		}
			
		
	}
 }
}

public User acceptaccountDetails(int acc) {
	try {
		System.out.println("Please enter your address : ");
		String address = sc.next();
		System.out.println("Please enter your phone number: ");
		String phonenumber = sc.next();
		int balance =0;
		System.out.println("Please enter password to create: ");
		String userPassword = sc.next();
		sc.nextLine();
		System.out.println("Please enter UserName: ");
		String userName = sc.next();

		
		User user = new User(acc, userName, address, phonenumber, balance, userPassword);
      
	}
	catch(InputMismatchException e) {
		System.out.println("Please check whether all details are entered correctly");
	}
      
	
     return user;
}
}
