package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Runner {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//Set up the driver
		Class.forName("com.mysql.jdbc.Driver");
		
		//Get a connection
		String url = "jdbc:mysql://localhost:3306/userdb";
		String username = "root";
		String password = "";
		
		Connection con = DriverManager.getConnection(url, username, password);
		
		String query = "SELECT username, password FROM user";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			System.out.println(rs.getString("username") + " " + rs.getString("password"));
		}
		
		stmt.close();
		con.close();
	}

}
