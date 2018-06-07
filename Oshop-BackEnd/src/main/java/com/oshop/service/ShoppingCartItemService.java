package com.oshop.service;

import java.util.List;
import java.util.Optional;

import com.oshop.model.ShoppingCartItem;

public interface ShoppingCartItemService {
	
	public List<ShoppingCartItem> getAll();
	
	public Optional<ShoppingCartItem> getById(Long id);
	
	public Optional<ShoppingCartItem> getByProductId(Long id);
	
	public void addShoppingCartItem(ShoppingCartItem item);
	
	public void deleteShoppingCart(Long id);

}
