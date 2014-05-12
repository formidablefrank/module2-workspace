package com.example.service;

import java.sql.SQLException;

import com.example.dao.UserDao;
import com.example.dao.UserDaoException;
import com.example.dao.UserDaoSqlImpl;
import com.example.model.User;

public class UserServiceImpl implements UserService {
	private UserDao ud;
	
	public UserServiceImpl(){
		ud = new UserDaoSqlImpl();
	}

	@Override
	public User findUserByUsername(String username) throws ClassNotFoundException, SQLException, UserDaoException {
		return ud.findUserByUsername(username);
	}

}
