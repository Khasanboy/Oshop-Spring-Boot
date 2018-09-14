package com.oshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oshop.model.Order;
import com.oshop.repository.OrderRepository;
import com.oshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired OrderRepository orderRepository;

	@Override
	public List<Order> getAllOrders() {
		return this.orderRepository.findAll();
	}

	@Override
	public Optional<Order> getOrderById(Long id) {
		return this.orderRepository.findById(id);
	}
	
	@Override
	public List<Order> getOrdersByUserId(Long id) {
		return this.orderRepository.getOrderByUserId(id);
	}

	@Override
	public Order addOrder(Order order) {
		return this.orderRepository.save(order);
	}
	
	@SuppressWarnings("unused")
	@Override
	public void deleteOrder(Long id) {
		this.orderRepository.deleteById(id);
	}
	
	@SuppressWarnings("unused")
	@Override
	public void updateOrder(Order order) {
		this.orderRepository.save(order);
	}


}
