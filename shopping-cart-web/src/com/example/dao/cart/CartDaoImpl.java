package com.example.dao.cart;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.example.dao.ConnectionManager;
import com.example.dao.DaoException;
import com.example.model.Cart;
import com.example.model.Product;

public class CartDaoImpl implements CartDao {

	public CartDaoImpl() {
		
	}

	@Override
	public Cart getCart(String username) throws SQLException, DaoException {
		Cart cart = null;
		Map<Product, Integer> list = new LinkedHashMap<>();
		
		Connection con = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM tbl_order_detail NATURAL JOIN tbl_order NATURAL JOIN tbl_product NATURAL JOIN tbl_user WHERE fld_username = ? AND fld_status = 'new';");
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Product pro = new Product(rs.getString("fld_product_name"), rs.getString("fld_category"), new BigDecimal(rs.getString("fld_unit_price")), rs.getString("fld_product_image"));
			list.put(pro, rs.getInt("fld_quantity"));
		}
		cart = new Cart(list, username, rs.getString("fld_status"), new BigDecimal(rs.getString("fld_amount")));
		
		stmt.close();
		con.close();
		return cart;
	}

	@Override
	public void addToCart(String username, String productname, int quantity)
			throws SQLException, DaoException {
		Connection con = ConnectionManager.getInstance().getConnection();
		
		PreparedStatement stmt3 = con.prepareStatement("SELECT key_order_detail FROM tbl_order NATURAL JOIN tbl_order_detail NATURAL JOIN tbl_user NATURAL JOIN tbl_product WHERE fld_product_name = ? AND fld_username = ?;");
		stmt3.setString(1, productname);
		stmt3.setString(1, username);
		ResultSet rs2 = stmt3.executeQuery();
		
	}

}