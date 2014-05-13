package com.example.dao.inventory;

import java.sql.SQLException;

import com.example.dao.DaoException;
import com.example.model.Inventory;

public interface InventoryDao {
	Inventory getInventory() throws DaoException, SQLException;
}
