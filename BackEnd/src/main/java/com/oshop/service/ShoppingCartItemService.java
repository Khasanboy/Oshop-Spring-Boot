package com.oshop.service;

import java.util.List;
import java.util.Optional;

import com.oshop.model.ShoppingCartItem;

public interface ShoppingCartItemService {
	
	@SuppressWarnings("unused")
	public List<ShoppingCartItem> getAll();
	
	public Optional<ShoppingCartItem> getById(Long id);
	
	public ShoppingCartItem addShoppingCartItem(ShoppingCartItem item);
	
	public void deleteShoppingCartItem(Long id);
	
	public ShoppingCartItem updateShoppingCartItem(ShoppingCartItem item);

}
