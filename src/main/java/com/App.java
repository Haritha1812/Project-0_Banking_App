package com;

import java.util.Scanner;

import com.banking.dao.EmployeeDAO;
import com.banking.dao.EmployeeDAOImplementation;

public class App 
{
    public static void main( String[] args )
    {
    	
	System.out.println("\n\n\n\t\t\t######### HSKP Banking App#######");
	System.out.println("\n\n\t\tHSKP Bank, India's leading private sector bank,"
			+ "offers Online NetBanking Services & Personal Banking Services like "
			+ "\n\t Accounts & Deposits, Cards, Loans, ...");

    BankApp bankapp = new BankApp();
	bankapp.startUserApp();

}
}
