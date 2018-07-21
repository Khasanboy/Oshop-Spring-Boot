package com.oshop.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.oshop.model.Shipping;
import com.oshop.model.ShoppingCartItem;

public class CreateOrderRequest {
	
	@NotBlank
	private Long userId;
	
	@NotBlank
	private Set<ShoppingCartItem> items = new HashSet<ShoppingCartItem>();
	
	private Shipping shipping;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(Set<ShoppingCartItem> items) {
		this.items = items;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

}
