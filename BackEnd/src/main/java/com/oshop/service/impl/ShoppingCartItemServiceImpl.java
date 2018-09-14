package com.oshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oshop.model.ShoppingCartItem;
import com.oshop.repository.ShoppingCartItemRepository;
import com.oshop.service.ShoppingCartItemService;

@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {
	
	@Autowired ShoppingCartItemRepository shoppingCartItemRepository;
	
	@SuppressWarnings("unused")
	@Override
	public List<ShoppingCartItem> getAll() {
		return this.shoppingCartItemRepository.findAll();
	}

	@Override
	public Optional<ShoppingCartItem> getById(Long id) {
		return shoppingCartItemRepository.findById(id);
	}

	@Override
	public ShoppingCartItem addShoppingCartItem(ShoppingCartItem item) {
		return this.shoppingCartItemRepository.save(item);
	}

	@Override
	public void deleteShoppingCartItem(Long id) {
		this.shoppingCartItemRepository.deleteById(id);
	}
	
	@Override
	public ShoppingCartItem updateShoppingCartItem(ShoppingCartItem item) {
		return this.shoppingCartItemRepository.save(item);
	}

}
