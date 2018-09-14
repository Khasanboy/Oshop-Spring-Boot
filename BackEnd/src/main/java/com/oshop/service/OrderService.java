package com.oshop.service;

import java.util.List;
import java.util.Optional;

import com.oshop.model.Order;

public interface OrderService {
	
	public List<Order> getAllOrders();
	
	public Optional<Order> getOrderById(Long id);
	
	public List<Order> getOrdersByUserId(Long id);
	
	public Order addOrder(Order order);
	
	@SuppressWarnings("unused")
	public void deleteOrder(Long id);
	
	@SuppressWarnings("unused")
	public void updateOrder(Order order);
	

}
