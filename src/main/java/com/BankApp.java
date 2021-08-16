bpackage com;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.banking.dao.CreatingAccount;
import com.banking.dao.EmployeeDAO;
import com.banking.dao.EmployeeDAOImplementation;
import com.banking.dao.Login;
import com.banking.dao.UserDAO;
import com.banking.dao.UserImplementation;
import com.banking.model.Employee;
import com.banking.model.User;
public class BankApp extends User {
	int choice = 0;
	Scanner sc = new Scanner(System.in);
	UserDAO userDAO = new UserImplementation();
	EmployeeDAO employeeDAO = new EmployeeDAOImplementation();
	boolean result;
      User user = new User();
      Employee employee = new Employee();
      CreatingAccount account = new CreatingAccount();
      private static Logger logger = Logger.getLogger("BankApp");
	public void startUserApp() {
	int ch =0;
		while(true){
			
			logger.info("\nWelcome to the HSKP Banking App");
			System.out.println("\n******M E N U*******");
			System.out.println("1.Login ");
			System.out.println("2.Create Account ");
			System.out.println("3.Know more about this app....");
			System.out.println("4.Know about developers..");
			System.out.println("9.E X I T ");
			System.out.println("Enter your choice : ");
			choice = sc.nextInt();
			Login login = new Login();
			switch(choice) {
			case 1:

				logger.info("\nYou are going to login page");
				login.loginToTheAccount();
				break;
				
				
			case 2:
				logger.info("\nYou are going to create account");
				account.createAccount();
				break;
				
			case 3:
				System.out.println("\t\t");
				System.out.println("\t\t********************************************");
				System.out.println("\t\t**   BANKING APP   **");
				System.out.println("\t\t********************************************");
				System.out.println("\r\n\t\t"
						+ "It is an app that helps the debit card holders to control the card status "
						+ "and daily transaction limits remotely through user-friendly menus");
				System.out.println("Signup to HSKP Bank API Banking Portal Sandbox environment to start your journey. Upgrade to UAT environment for end to end real time testing , post your NDA with us. Go-live with integration to production environment.\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "*Disclaimer : Access to UAT & Production environment is subject to business approvals, NDA and other agreements, and is a sole discretion of HSKP Bank.");
				break;
				
			case 4:
				System.out.println("   \nDeveloped by Haritha and team  \n Technology is at the core of what we do.\n And it’s changing the way our FinTech Partners do business.");
				  break;
				  
			case 9:
				System.exit(0);
				
				}
		}
	
	}
}

