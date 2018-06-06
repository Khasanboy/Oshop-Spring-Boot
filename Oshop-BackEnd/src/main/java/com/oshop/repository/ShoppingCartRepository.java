package com.oshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oshop.model.ShoppingCart;
import com.oshop.model.ShoppingCartItem;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{
	
	Optional<ShoppingCartItem> findByItemsId(Long id);
	List<ShoppingCartItem> findByItems();
}
