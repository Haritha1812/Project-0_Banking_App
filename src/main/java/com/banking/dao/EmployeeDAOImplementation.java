package com.banking.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.banking.model.Employee;
import com.banking.model.User;

public class EmployeeDAOImplementation implements EmployeeDAO {
	Connection connection = com.banking.util.DBConnection.getDBConnection();
	Scanner sc = new Scanner(System.in);
	private final String ADD_EMPLOYEE_ACCOUNT_QUERY = "insert into hr.Employee values(?,?,?,?,?)";
	private final String VIEW_ALL_USERDETAILS = "select * from hr.Users";
	private final String DELETE_EMPLOYEE_ACCOUNT_QUERY = "delete from hr.Employee where employeeid = ?";
	private final String UPDATE_EMPLOYEE_ACCOUNT_QUERY = "update hr.Employee set employeeName = ?,employeeAddress = ?,employeePhoneNumber = ?, employeePassword = ? where employeeId = ?";
	
	// method to add employee details
	public boolean addEmployeeAccount(final Employee employee) {
		int res = 0;
		try {
		    PreparedStatement statement = connection.prepareStatement(ADD_EMPLOYEE_ACCOUNT_QUERY);
			statement.setInt(1, employee.getEmployeeId());
			statement.setString(2, employee.getEmployeeName());
			statement.setString(3, employee.getEmployeeAddress());
			statement.setString(4, employee.getEmployeePhoneNumber());
			statement.setString(5, employee.getEmployeePassword());

			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		         if (res == 0)
			        return false;
		else
			return true;
	
	}
    //  method to delete employee account
	public boolean deleteEmployeeAccount( int employeeId) {
            int res =0;
		
		PreparedStatement st;
		try {
			st = connection.prepareStatement(DELETE_EMPLOYEE_ACCOUNT_QUERY);
			st.setInt(1,employeeId);
			res = st.executeUpdate();
		} catch ( SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(res ==0)
		return false;
		else
			return true;
		
	}
    //method to update account details
	public boolean updateEmployeeAccount(final Employee employee) {
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_ACCOUNT_QUERY);
			
			statement.setString(1, employee.getEmployeeName());
			statement.setString(2, employee.getEmployeeAddress());
			statement.setString(3, employee.getEmployeePhoneNumber());
			statement.setString(4, employee.getEmployeePassword());
			statement.setInt(5, employee.getEmployeeId());

			res = statement.executeUpdate();
		} catch ( SQLException e) {
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;
	}

	
    // method to vaildate account for employee for login
	public Employee validateAccount(final int employeeId, final String employeePassword) {
	Employee employee=null;
		try {
			PreparedStatement statement=connection.prepareStatement("select * from hr.Employee where employeeId=? and employeePassword=?");
			statement.setInt(1, employeeId);
			statement.setString(2,employeePassword);
		    ResultSet resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				employee=new Employee();
				employee.setEmployeeId(resultSet.getInt(1));
				employee.setEmployeeName(resultSet.getString(2));
				employee.setEmployeePassword(resultSet.getString(4));
				employee.setEmployeePhoneNumber(resultSet.getString(3));
				employee.setEmployeeAddress(resultSet.getString(5));
								
			}
		} 
		catch (final SQLException e) {
		
		}
		
		return employee;
		
	    }
   ///method to view the account of employee
	public Employee viewEmployeeAccount(final int employeeId) {
		Employee employee=null;
		try {
			PreparedStatement statement=connection.prepareStatement("select * from hr.Employee where employeeId=? ");
			statement.setInt(1, employeeId);
		    ResultSet resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				employee=new Employee();
				employee.setEmployeeId(resultSet.getInt(1));
				employee.setEmployeeName(resultSet.getString(2));
				employee.setEmployeePassword(resultSet.getString(5));
				employee.setEmployeePhoneNumber(resultSet.getString(4));
				employee.setEmployeeAddress(resultSet.getString(3));
							
			}
		} 
		catch ( SQLException e) {
			
		}
		System.out.println(employee);
		return employee;
		
		
      	}

	//method to get all the details of the user table
	public void getAllUserDetails() {
		
    	Statement statement = null;
    	ResultSet rs =null;
    	
		
		 try {
			statement = connection.createStatement();
			DatabaseMetaData dbmd = connection.getMetaData();
			
			rs = statement.executeQuery("select * from  hr.Users");
		    ResultSetMetaData rsmd = rs.getMetaData();
		    int col = rsmd.getColumnCount();
		       while(rs.next()) {
			       for(int i =1; i<=col ;i++) {
				System.out.print(rs.getString(i)+ "  ");
			           }
		             System.out.println();//Move to the next line to print the next row.           
		    	    }}
		 catch ( SQLException e) {
			
			e.printStackTrace();
		     }
      
		     }
	
	// method to get the details of user
	public List<User> getAllUserDetailsByList(){
	// creating list to get the details of the user	
	 List <User> users = new ArrayList<User>();
		try {
			
		       Statement st = null;
			   st = connection.createStatement();
		       ResultSet rs = st.executeQuery(VIEW_ALL_USERDETAILS);
		       while(rs.next()) {
			   
			   User user = new User();
			   user.setAccountNumber(rs.getInt(1));
			   user.setUserName(rs.getString(2));
			   user.setAddress(rs.getString(3));
			   user.setPhoneNumber(rs.getString(4));
			   user.setBalance(rs.getInt(5));
			   user.setUserPassword(rs.getString(6));
			   users.add(user);
			   
		         }
		        }
		 catch ( SQLException e) {
		
			e.printStackTrace();
		        }
		
		      return users;
	            }
    
	// method to view transaction details
	public void viewTransactionDetails() {
		
		Statement statement = null;
    	ResultSet rs =null;
    	
		
		 try {
			         statement = connection.createStatement();
			         DatabaseMetaData dbmd = connection.getMetaData();
			
			         rs = statement.executeQuery("select * from  hr.customerTransaction");
		             ResultSetMetaData rsmd = rs.getMetaData();
		             int colCount=rsmd.getColumnCount();

		             String arr[]={"AccountNo","UserName","ToAccountNo", "Withdraw"," Deposit", "Transfund","Availbal","Transcation Date","Transcation Time"};
		 	        System.out.println();
		 	        for(int j=0;j<9;j++)
		 	        {
		 	            System.out.print(arr[j]);
		 	            System.out.print("   ");
		 	        }
		 	        System.out.println();
		 	        while(rs.next())
		 	        {
		 	            for(int i=1;i<=colCount;i++)
		 	            {
		 	                String len=rs.getString(i);
		 	                if(rs.getString(i).equals("0"))
		 	                    System.out.print(".....");
		 	                else{
		 	                System.out.print(rs.getString(i));
		 	                }
		 	                for(int j=1;j<5;j++)
		 	                {
		 	                        System.out.print("  ");
		 	                }
		 	            }
		 	            System.out.println();
		 	        }}
		              catch ( SQLException e) {
			
		              e.printStackTrace();
		              }
		  
		
		
	}
	public void viewStatementOfUserById(int accountnumber) {
		
	    {
	        PreparedStatement  statement = null;
	        ResultSet rs =null;
	       try {
	        statement=connection.prepareStatement("select c.accountnumber,username,reciveraccountNumber,withdrawamount,depositamount,transferamount,c.balance,transactiondate , transtime from hr.Users c join hr.customerTransaction t on t.accountnumber=c.accountnumber where c.accountnumber = ?;");
	        statement.setInt(1,accountnumber);
	        DatabaseMetaData databaseMetaData=connection.getMetaData();
	        rs=statement.executeQuery();
	        ResultSetMetaData metaData=rs.getMetaData();
	        int colCount=metaData.getColumnCount();

	        String arr[]={"AccountNo","UserName","ToAccountNo", "Withdraw"," Deposit", "Transfund","Availbal","Transcation Date","Transcation Time"};
	        System.out.println();
	        for(int j=0;j<9;j++)
	        {
	            System.out.print(arr[j]);
	            System.out.print("   ");
	        }
	        System.out.println();
	        while(rs.next())
	        {
	            for(int i=1;i<=colCount;i++)
	            {
	                String len=rs.getString(i);
	                if(rs.getString(i).equals("0"))
	                    System.out.print(".....");
	                else{
	                System.out.print(rs.getString(i));
	                }
	                for(int j=1;j<5;j++)
	                {
	                        System.out.print("  ");
	                }
	            }
	            System.out.println();
	        }
	       }
	       
	       catch (SQLException e) {
	     
	        e.printStackTrace();
	    }
	           
	}
	}
}	


	
	
