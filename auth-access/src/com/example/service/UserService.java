package com.example.service;

import com.example.model.User;

public interface UserService {
	User findUserByUsername(String username);
}