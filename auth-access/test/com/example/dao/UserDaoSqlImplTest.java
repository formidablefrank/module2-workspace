package com.example.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.example.model.User;

public class UserDaoSqlImplTest {

	@Test
	public void testFindUserByUsername() {
		UserDao ud = new UserDaoSqlImpl();
		User user = new User("admin", "admin");
		try {
			assertEquals(user, ud.findUserByUsername("admin"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
