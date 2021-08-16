package com.banking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DBConnection {

	// to get connection to the database
		public static Connection getDBConnection() {
			Connection connection = null;
			PreparedStatement st = null;
		
			try {
				Class.forName("org.postgresql.Driver");
				
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","root");
				
				
			     } 
			catch (ClassNotFoundException e) {
				System.out.println("not loaded");
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;

			}
		
		public static void main(String[] args) {
			
		}

	}
