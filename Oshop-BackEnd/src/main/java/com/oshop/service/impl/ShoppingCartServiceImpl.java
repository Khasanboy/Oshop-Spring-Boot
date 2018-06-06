package com.oshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oshop.model.ShoppingCart;
import com.oshop.repository.ShoppingCartRepository;
import com.oshop.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
		
	@Autowired ShoppingCartRepository shoppingCartRepository;
	
	@Override
	public List<ShoppingCart> getAll() {
		return this.shoppingCartRepository.findAll();
	}

	@Override
	public Optional<ShoppingCart> getById(Long id) {
		return this.shoppingCartRepository.findById(id);
	}

	@Override
	public ShoppingCart addShoppingCart(ShoppingCart shoppingCart) {
		return this.shoppingCartRepository.save(shoppingCart);
	}

	@Override
	public void deleteShoppingCart(Long id) {
		this.shoppingCartRepository.deleteById(id);
	}

}
