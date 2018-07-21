package com.oshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oshop.model.ShoppingCartItem;

public interface ShoppingCartItemRepository  extends JpaRepository<ShoppingCartItem, Long> {
	
	Optional<ShoppingCartItem> findByProductId(Long id);
}
