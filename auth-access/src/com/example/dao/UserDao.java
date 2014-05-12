package com.example.dao;

import java.sql.SQLException;

import com.example.model.User;

public interface UserDao {
	User findUserByUsername(String username) throws ClassNotFoundException, SQLException, UserDaoException;
}
