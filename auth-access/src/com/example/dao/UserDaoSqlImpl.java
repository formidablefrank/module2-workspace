package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.User;

public class UserDaoSqlImpl implements UserDao {

	@Override
	public User findUserByUsername(String name) {
		User user = null;
		try {
			//Set up the driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Get a connection
			String url = "jdbc:mysql://localhost:3306/userdb";
			String username = "root";
			String password = "";
			
			Connection con = DriverManager.getConnection(url, username, password);
			
			PreparedStatement stmt = con.prepareStatement("SELECT username, password from user WHERE username = ? ;");
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				user = new User(rs.getString("username"), rs.getString("password"));
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
