package com.deepankar.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;



public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
		String user = "root";
		String pass = "D10j0n@th@n";
		 
		try {
		
			System.out.println("Connecting to database" + jdbcUrl);
			@SuppressWarnings("unused")
			Connection myCon = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection Successful!");
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}
		
		
		
	}

}
  