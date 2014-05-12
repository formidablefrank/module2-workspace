package com.example.service;

import java.sql.SQLException;

import com.example.dao.UserDaoException;
import com.example.model.User;

public interface UserService {
	User findUserByUsername(String username) throws ClassNotFoundException, SQLException, UserDaoException;
}
