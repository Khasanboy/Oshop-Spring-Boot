package com.oshop.controller;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshop.model.Product;
import com.oshop.model.ShoppingCart;
import com.oshop.model.ShoppingCartItem;
import com.oshop.payload.CreateShoppingCartItemRequest;
import com.oshop.payload.CreateShoppingCartRequest;
import com.oshop.service.ProductService;
import com.oshop.service.ShoppingCartItemService;
import com.oshop.service.ShoppingCartService;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ShoppingCartItemService shoppingCartItemService;
	
	@Autowired 
	ProductService productService;

	@GetMapping("/{id}")
	public ShoppingCart getOneById(@PathVariable Long id) {

		return this.shoppingCartService.getById(id).orElse(null);

	}

	@GetMapping("/")
	public List<ShoppingCart> getAll() {
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

	@GetMapping("/{cartId}/items/{productId}")
	public CreateShoppingCartItemRequest getShoppingCartItem(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId) {
		System.out.println("Came  cardId"+ cartId+ " productId "+productId);
		Set<ShoppingCartItem> items = this.shoppingCartService.getById(cartId).orElse(null).getItems();

		for (ShoppingCartItem cartItem : items) {
			System.out.println(cartItem.getProduct().getId());
			if (cartItem.getProduct().getId() == productId) {
				return new CreateShoppingCartItemRequest(cartItem.getId(), cartItem.getQuantity(), cartItem.getProduct().getId());
			}
		}

		return null;
	}
	
	@PostMapping("/{cartId}/items")
	public ShoppingCartItem createShoppingCartItem(@PathVariable Long cartId, @RequestBody CreateShoppingCartItemRequest request) {
		System.out.println("Came post "+ request.getId());
		System.out.println("Product id "+ request.getProductId());
		System.out.println("Quantity "+request.getQuantity());
		Product product = this.productService.getProductById(request.getProductId()).orElse(null);
		ShoppingCartItem result;
		if(product!= null) {
			ShoppingCartItem item = new ShoppingCartItem();
			//item.setId(request.getProductId());
			item.setQuantity(request.getQuantity());
			item.setProduct(product);
			result = this.shoppingCartItemService.addShoppingCartItem(item);
			this.shoppingCartService.getById(cartId).get().addItem(result);			
			return this.shoppingCartItemService.addShoppingCartItem(item);
		}
		
		return null;
	}
	

	@PutMapping("/{cartId}/items/{productId}")
	public ShoppingCartItem updateShoppingCartItem(@PathVariable Long cartId, @PathVariable Long productId,
			@RequestBody CreateShoppingCartItemRequest request) {
		System.out.println("Cameee");
		Set<ShoppingCartItem> items = this.shoppingCartService.getById(cartId).get().getItems();
		for(ShoppingCartItem item: items) {
			if (item.getProduct().getId() == productId) {
				item.setQuantity(request.getQuantity());
				return this.shoppingCartItemService.updateShoppingCartItem(item);
			}
		}

		return null;

	}
	
	
}
