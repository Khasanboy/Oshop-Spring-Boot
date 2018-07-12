package com.oshop.service;

import java.util.List;
import java.util.Optional;

import com.oshop.model.Order;

public interface OrderService {
	
	public List<Order> getAllOrders();
	
	public Optional<Order> getOrderById(Long id);
	
	public void addOrder(Order order);
	
	public void deleteOrder(Long id);
	
	public void updateOrder(Order order);
	

}
