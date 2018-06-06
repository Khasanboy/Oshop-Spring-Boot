package com.oshop.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshop.model.ShoppingCart;
import com.oshop.payload.CreateShoppingCartRequest;
import com.oshop.service.ShoppingCartService;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {
	
	@Autowired ShoppingCartService shoppingCartService;
	
	@GetMapping("/{id}")
	public ShoppingCart getOneById(@PathVariable Long id) {
		
		return this.shoppingCartService.getById(id).orElse(null);
		
	}
	
	@GetMapping("/")
	public List<ShoppingCart> getAll(){
		return this.shoppingCartService.getAll();
	}
	
	@PostMapping("/")
	public ShoppingCart createShoppingCart(@RequestBody CreateShoppingCartRequest cartRequest) {
		
		ShoppingCart cart = new ShoppingCart();	
		cart.setCreatedAt(Instant.now());
		
		return this.shoppingCartService.addShoppingCart(cart);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteShoppingCart(@PathVariable Long id) {
		
		this.shoppingCartService.deleteShoppingCart(id);
		
	}
	
	
}
