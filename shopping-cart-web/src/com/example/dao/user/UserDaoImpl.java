package com.example.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.dao.ConnectionManager;
import com.example.dao.DaoException;
import com.example.model.User;

public class UserDaoImpl implements UserDao {

	public UserDaoImpl() {
		
	}

	@Override
	public User getUser(String name) throws DaoException, SQLException {
		User user = null;
		
		Connection con = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT fld_username, fld_password, fld_roletype FROM tbl_user NATURAL JOIN tbl_role WHERE fld_username = ? ;");
		stmt.setString(1, name);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			user = new User(rs.getString("fld_username"), rs.getString("fld_password"), rs.getString("fld_roletype"));
		}
		
		stmt.close();
		con.close();
		
		return user;
	}

}
