package com.example.dao;

import com.example.model.User;

public interface UserDao {
	User findUserByUsername(String username);
}
