package com.example.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.model.User;

public class UserDaoSqlImplTest {

	@Test
	public void testFindUserByUsername() {
		UserDao ud = new UserDaoSqlImpl();
		User user = new User("admin", "admin");
		assertEquals(user, ud.findUserByUsername("admin"));
	}

}
