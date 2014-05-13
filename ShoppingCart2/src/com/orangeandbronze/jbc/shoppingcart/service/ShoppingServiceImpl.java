package com.orangeandbronze.jbc.shoppingcart.service;

import java.math.BigDecimal;
import java.util.List;

import com.orangeandbronze.jbc.shoppingcart.dao.ShoppingDao;
import com.orangeandbronze.jbc.shoppingcart.dao.ShoppingDaoException;
import com.orangeandbronze.jbc.shoppingcart.dao.ShoppingDaoImpl;
import com.orangeandbronze.jbc.shoppingcart.model.CartProduct;
import com.orangeandbronze.jbc.shoppingcart.model.InventoryProduct;

public class ShoppingServiceImpl implements ShoppingService {
	private ShoppingDao shoppingDao;

	public ShoppingServiceImpl() {
		shoppingDao = new ShoppingDaoImpl();
	}

	@Override
	public List<InventoryProduct> getInventory() throws ShoppingDaoException {
		return shoppingDao.getInventory();
	}

	@Override
	public List<CartProduct> getCart() throws ShoppingDaoException {
		return shoppingDao.getCart();
	}

	@Override
	public void buy(int productNo, int quantity)
			throws ShoppingServiceException, ShoppingDaoException {
		List<InventoryProduct> inventory = getInventory();
		List<CartProduct> cart = getCart();
		InventoryProduct boughtProduct = (InventoryProduct) searchList(inventory, productNo);
		if(boughtProduct == null){
			throw new ShoppingServiceException("Invalid product number! Try again.");
		}
		else if(quantity < 1){
			throw new ShoppingServiceException("Invalid quantity number! Try again.");
		}
		else if(quantity > boughtProduct.getQuantity()){
			throw new ShoppingServiceException("Insufficient supply! Try again.");
		}
		else {
			inventory.remove(boughtProduct);
			inventory.add(new InventoryProduct(boughtProduct.getProduct(), boughtProduct.getQuantity() - quantity));
			CartProduct checkProduct = (CartProduct) searchList(cart, productNo);
			if(checkProduct == null){
				cart.add(new CartProduct(boughtProduct.getProduct(), quantity));
			} else {
				cart.remove(checkProduct);
				cart.add(new CartProduct(checkProduct.getProduct(), checkProduct.getQuantity() + quantity));
			}
			updateDatabase(cart, inventory);
		}
	}
	
	private Object searchList(List<?> collection, int productNo){
		if(productNo > 0 && productNo <= collection.size()){
			return null;
		} else {
			return collection.get(productNo - 1);
		}
	}

	@Override
	public boolean isInventoryEmpty() throws ShoppingDaoException {
		return isCollectionEmpty(getInventory());
	}

	@Override
	public boolean isCartEmpty() throws ShoppingDaoException {
		return isCollectionEmpty(getCart());
	}
	
	private boolean isCollectionEmpty(List<?> collection){
		return collection.isEmpty() || getTotalQuantity(collection) == 0;
	}

	@Override
	public void updateDatabase(List<CartProduct> cart, List<InventoryProduct> inventory) throws ShoppingDaoException {
		shoppingDao.updateCart(cart);
		shoppingDao.updateInventory(inventory);
	}

	@Override
	public BigDecimal getCartGrandTotal() throws ShoppingDaoException {
		return getGrandTotal(getCart());
	}
	
	public BigDecimal getInventoryGrandTotal() throws ShoppingDaoException {
		return getGrandTotal(getInventory());
	}
	
	private BigDecimal getGrandTotal(List<?> collection) {
		BigDecimal grandTotal = new BigDecimal("0.00");
		for(Object item: collection) {
			if(item instanceof CartProduct) grandTotal = grandTotal.add(((CartProduct) item).getProduct().getPrice().multiply(new BigDecimal(((CartProduct)item).getQuantity())));
			else if(item instanceof InventoryProduct) grandTotal = grandTotal.add(((InventoryProduct) item).getProduct().getPrice().multiply(new BigDecimal(((InventoryProduct)item).getQuantity())));
		}
		return grandTotal;
	}
	
	private int getTotalQuantity(List<?> collection){
		int total = 0;
			for(Object item: collection){
				if(item instanceof InventoryProduct)
					total += ((InventoryProduct) item).getQuantity();
				else if(item instanceof CartProduct)
					total += ((CartProduct) item).getQuantity();
			}
		return total;
	}

}
