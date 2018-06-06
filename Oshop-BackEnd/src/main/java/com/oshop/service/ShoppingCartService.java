package com.oshop.service;

import java.util.List;
import java.util.Optional;

import com.oshop.model.ShoppingCart;

public interface ShoppingCartService {
	
	public List<ShoppingCart> getAll();
	
	public Optional<ShoppingCart> getById(Long id);
	
	public ShoppingCart addShoppingCart(ShoppingCart shoppingCart);
	
	public void deleteShoppingCart(Long id);

}
