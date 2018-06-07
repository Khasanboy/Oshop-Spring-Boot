package com.oshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.oshop.model.ShoppingCartItem;
import com.oshop.repository.ShoppingCartItemRepository;
import com.oshop.service.ShoppingCartItemService;

public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {
	
	@Autowired ShoppingCartItemRepository shoppingCartItemRepository;
	
	@Override
	public List<ShoppingCartItem> getAll() {
		return this.shoppingCartItemRepository.findAll();
	}

	@Override
	public Optional<ShoppingCartItem> getById(Long id) {
		return shoppingCartItemRepository.findById(id);
	}

	@Override
	public void addShoppingCartItem(ShoppingCartItem item) {
		this.shoppingCartItemRepository.save(item);
	}

	@Override
	public void deleteShoppingCart(Long id) {
		this.shoppingCartItemRepository.deleteById(id);
	}

	@Override
	public Optional<ShoppingCartItem> getByProductId(Long id) {
		return this.shoppingCartItemRepository.findByProductId(id);  
	}

}
