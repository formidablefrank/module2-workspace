package com.orangeandbronze.jbc.shoppingcart.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.orangeandbronze.jbc.shoppingcart.model.CartProduct;
import com.orangeandbronze.jbc.shoppingcart.model.InventoryProduct;
import com.orangeandbronze.jbc.shoppingcart.model.Product;

public class ShoppingDaoImpl implements ShoppingDao {
	private final String CART_FILE = "resources/cart";
	private final String INVENTORY_FILE = "resources/inventory";
	
	private List get(String filename) throws ShoppingDaoException{
		List list = filename.equals(INVENTORY_FILE) ? new ArrayList<InventoryProduct>() : new ArrayList<CartProduct>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
			int productNo = 1;
			while(reader.ready()){
				String temp[] = reader.readLine().split("\\s+");
				Product newProduct = new Product(temp[0], productNo, "", new BigDecimal(temp[2]));
				Object newItem = null;
				if (filename.equals(INVENTORY_FILE)){
					newItem = new InventoryProduct(newProduct, Integer.parseInt(temp[1]));
				}
				else{
					newItem = new CartProduct(newProduct, Integer.parseInt(temp[1]));
				}
				list.add(newItem);
				productNo++;
			}
		} catch(IOException e){
			throw new ShoppingDaoException("ERROR: Reading file failed");
		}
		return list;
	}
	
	@Override
	public List<InventoryProduct> getInventory() throws ShoppingDaoException{
		return get(INVENTORY_FILE);
	}

	@Override
	public List<CartProduct> getCart() throws ShoppingDaoException {
		return get(CART_FILE);
	}
	
	private void update(List<?> collection, String filename) throws ShoppingDaoException{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
			for(Object item: collection){
				Product pro = null;
				String temp = null;
				if(item instanceof InventoryProduct){
					pro = ((InventoryProduct) item).getProduct();
					temp = String.format("%s\t%d\t%s", pro.getName(), ((InventoryProduct) item).getQuantity(), pro.getPrice().toPlainString());
				}
				if(item instanceof CartProduct){
					pro = ((CartProduct) item).getProduct();
					temp = String.format("%s\t%d\t%s", pro.getName(), ((CartProduct) item).getQuantity(), pro.getPrice().toPlainString());
				}
				writer.write(temp);
				writer.newLine();
			}
		} catch (IOException e){
			throw new ShoppingDaoException("ERROR: Writing file failed");
		}
	}

	@Override
	public void updateInventory(List<InventoryProduct> collection) throws ShoppingDaoException{
		update(collection, INVENTORY_FILE);
	}

	@Override
	public void updateCart(List<CartProduct> collection)
			throws ShoppingDaoException {
		update(collection, CART_FILE);
	}
}
